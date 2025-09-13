package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;
import java.util.function.Function;

/**
 * Utility class for advanced wait operations and custom wait conditions
 */
public class WaitUtils {
    
    // Default wait timeouts from config
    private static final int DEFAULT_WAIT_TIMEOUT = ConfigReader.getInt("web.wait.timeout", 10);
    private static final int SHORT_WAIT_TIMEOUT = ConfigReader.getInt("web.wait.short.timeout", 5);
    private static final int LONG_WAIT_TIMEOUT = ConfigReader.getInt("web.wait.long.timeout", 20);
    private static final int PAGE_LOAD_TIMEOUT = ConfigReader.getInt("web.wait.page.load.timeout", 30);
    
    /**
     * Create a WebDriverWait with default timeout
     */
    public static WebDriverWait getDefaultWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIMEOUT));
    }
    
    /**
     * Create a WebDriverWait with short timeout
     */
    public static WebDriverWait getShortWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(SHORT_WAIT_TIMEOUT));
    }
    
    /**
     * Create a WebDriverWait with long timeout
     */
    public static WebDriverWait getLongWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_WAIT_TIMEOUT));
    }
    
    /**
     * Create a WebDriverWait with custom timeout
     */
    public static WebDriverWait getCustomWait(WebDriver driver, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }
    
    /**
     * Create a FluentWait with custom polling interval
     */
    public static FluentWait<WebDriver> getFluentWait(WebDriver driver, int timeoutInSeconds, int pollingIntervalInMillis) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingIntervalInMillis))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }
    
    /**
     * Wait for element to be visible and enabled
     */
    public static Boolean waitForElementToBeVisibleAndEnabled(WebDriver driver, By locator) {
        WebDriverWait wait = getDefaultWait(driver);
        try {
            wait.until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(locator),
                    ExpectedConditions.elementToBeClickable(locator)
            ));
            return true;  // Element visible and clickable within timeout
        } catch (TimeoutException e) {
            return false; // Element not visible or clickable within timeout
        }
    }
    
    /**
     * Wait for element to be visible and enabled with custom timeout
     */
    public static boolean isElementVisibleAndEnabled(WebDriver driver, By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(locator),
                    ExpectedConditions.elementToBeClickable(locator)
            ));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    
    /**
     * Wait for element to have specific text
     */
    public static boolean waitForElementToHaveText(WebDriver driver, By locator, String expectedText) {
        WebDriverWait wait = getDefaultWait(driver);
        return wait.until(ExpectedConditions.textToBe(locator, expectedText));
    }
    
    /**
     * Wait for element to have specific text with custom timeout
     */
    public static boolean waitForElementToHaveText(WebDriver driver, By locator, String expectedText, int timeoutInSeconds) {
        WebDriverWait wait = getCustomWait(driver, timeoutInSeconds);
        return wait.until(ExpectedConditions.textToBe(locator, expectedText));
    }
    
    /**
     * Wait for element to have specific attribute value
     */
    public static boolean waitForElementToHaveAttribute(WebDriver driver, By locator, String attribute, String value) {
        WebDriverWait wait = getDefaultWait(driver);
        return wait.until(ExpectedConditions.attributeToBe(locator, attribute, value));
    }
    
    /**
     * Wait for element to have specific attribute value with custom timeout
     */
    public static boolean waitForElementToHaveAttribute(WebDriver driver, By locator, String attribute, String value, int timeoutInSeconds) {
        WebDriverWait wait = getCustomWait(driver, timeoutInSeconds);
        return wait.until(ExpectedConditions.attributeToBe(locator, attribute, value));
    }
    
    /**
     * Wait for element to be selected (for checkboxes, radio buttons)
     */
    public static boolean waitForElementToBeSelected(WebDriver driver, By locator) {
        WebDriverWait wait = getDefaultWait(driver);
        return wait.until(ExpectedConditions.elementToBeSelected(locator));
    }
    
    /**
     * Wait for element to be selected with custom timeout
     */
    public static boolean waitForElementToBeSelected(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = getCustomWait(driver, timeoutInSeconds);
        return wait.until(ExpectedConditions.elementToBeSelected(locator));
    }
    
    /**
     * Wait for element to be not selected
     */
    public static boolean waitForElementToBeNotSelected(WebDriver driver, By locator) {
        WebDriverWait wait = getDefaultWait(driver);
        return wait.until(ExpectedConditions.elementSelectionStateToBe(locator, false));
    }
    
    /**
     * Wait for element to be not selected with custom timeout
     */
    public static boolean waitForElementToBeNotSelected(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = getCustomWait(driver, timeoutInSeconds);
        return wait.until(ExpectedConditions.elementSelectionStateToBe(locator, false));
    }
    
    /**
     * Wait for frame to be available and switch to it
     */
    public static WebDriver waitForFrameAndSwitchToIt(WebDriver driver, By frameLocator) {
        WebDriverWait wait = getDefaultWait(driver);
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }
    
    /**
     * Wait for frame to be available and switch to it with custom timeout
     */
    public static WebDriver waitForFrameAndSwitchToIt(WebDriver driver, By frameLocator, int timeoutInSeconds) {
        WebDriverWait wait = getCustomWait(driver, timeoutInSeconds);
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }
    
    /**
     * Wait for window to open and switch to it
     */
    public static boolean waitForNewWindowAndSwitchToIt(WebDriver driver, int expectedWindowCount) {
        WebDriverWait wait = getDefaultWait(driver);
        return wait.until(ExpectedConditions.numberOfWindowsToBe(expectedWindowCount));
    }
    
    /**
     * Wait for window to open and switch to it with custom timeout
     */
    public static boolean waitForNewWindowAndSwitchToIt(WebDriver driver, int expectedWindowCount, int timeoutInSeconds) {
        WebDriverWait wait = getCustomWait(driver, timeoutInSeconds);
        return wait.until(ExpectedConditions.numberOfWindowsToBe(expectedWindowCount));
    }
    
    /**
     * Wait for page to load completely using JavaScript
     */
    public static boolean waitForPageToLoad(WebDriver driver) {
        WebDriverWait wait = getCustomWait(driver, PAGE_LOAD_TIMEOUT);
        return wait.until(webDriver -> 
            ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }
    
    /**
     * Wait for page to load completely with custom timeout
     */
    public static boolean waitForPageToLoad(WebDriver driver, int timeoutInSeconds) {
        WebDriverWait wait = getCustomWait(driver, timeoutInSeconds);
        return wait.until(webDriver -> 
            ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }
    
    /**
     * Wait for jQuery to finish loading (if jQuery is used)
     */
    public static boolean waitForJQueryToLoad(WebDriver driver) {
        WebDriverWait wait = getDefaultWait(driver);
        return wait.until(webDriver -> 
            (Boolean) ((JavascriptExecutor) webDriver).executeScript("return jQuery.active == 0")
        );
    }
    
    /**
     * Wait for jQuery to finish loading with custom timeout
     */
    public static boolean waitForJQueryToLoad(WebDriver driver, int timeoutInSeconds) {
        WebDriverWait wait = getCustomWait(driver, timeoutInSeconds);
        return wait.until(webDriver -> 
            (Boolean) ((JavascriptExecutor) webDriver).executeScript("return jQuery.active == 0")
        );
    }
    
    /**
     * Wait for Angular to finish loading (if Angular is used)
     */
    public static boolean waitForAngularToLoad(WebDriver driver) {
        WebDriverWait wait = getDefaultWait(driver);
        return wait.until(webDriver -> 
            (Boolean) ((JavascriptExecutor) webDriver).executeScript(
                "return angular.element(document).injector().get('$http').pendingRequests.length === 0"
            )
        );
    }
    
    /**
     * Wait for Angular to finish loading with custom timeout
     */
    public static boolean waitForAngularToLoad(WebDriver driver, int timeoutInSeconds) {
        WebDriverWait wait = getCustomWait(driver, timeoutInSeconds);
        return wait.until(webDriver -> 
            (Boolean) ((JavascriptExecutor) webDriver).executeScript(
                "return angular.element(document).injector().get('$http').pendingRequests.length === 0"
            )
        );
    }
    
    /**
     * Wait for element to be stale (useful for dynamic content)
     */
    public static boolean waitForElementToBeStale(WebDriver driver, WebElement element) {
        WebDriverWait wait = getDefaultWait(driver);
        return wait.until(ExpectedConditions.stalenessOf(element));
    }
    
    /**
     * Wait for element to be stale with custom timeout
     */
    public static boolean waitForElementToBeStale(WebDriver driver, WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = getCustomWait(driver, timeoutInSeconds);
        return wait.until(ExpectedConditions.stalenessOf(element));
    }
    
    /**
     * Wait for element to be refreshed and clickable
     */
    public static WebElement waitForElementToBeRefreshedAndClickable(WebDriver driver, By locator) {
        WebDriverWait wait = getDefaultWait(driver);
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
    }
    
    /**
     * Wait for element to be refreshed and clickable with custom timeout
     */
    public static WebElement waitForElementToBeRefreshedAndClickable(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = getCustomWait(driver, timeoutInSeconds);
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
    }

}
