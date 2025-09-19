package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    // Keeps a separate WebDriver instance per test thread.
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = System.getProperty("browser", "chrome").toLowerCase();
            boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

            switch (browser) {
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (isHeadless) {
                        firefoxOptions.addArguments("-headless");
                    }
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (isHeadless) {
                        edgeOptions.addArguments("headless");
                    }
                    driver.set(new EdgeDriver(edgeOptions));
                    break;

                case "chrome":
                default:
                    // Set Chrome preferences
                    Map<String, Object> prefs = new HashMap<>();

//                    String downloadFilepath = System.getProperty("user.dir") + File.separator + "downloads";
                    String downloadFilepath = System.getProperty("user.dir") + "/downloads/";
                    File file = new File(downloadFilepath);
                    if (!file.exists()) file.mkdirs();
                    System.out.println(downloadFilepath);

                    prefs.put("download.default_directory", downloadFilepath);  // ⬅️ Set your folder path
                    prefs.put("download.prompt_for_download", false);
                    prefs.put("safebrowsing.enabled", true);  // Avoid "keep file" warnings
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setExperimentalOption("prefs", prefs);
                    if (isHeadless) {
                        chromeOptions.addArguments("--headless=new"); // for latest Chrome versions
                        chromeOptions.addArguments("--window-size=1920,1080");

                    }
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
            }
        }
        return driver.get();
    }


    public static void quitDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
        }
    }
}
