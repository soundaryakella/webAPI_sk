package listeners;

import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverFactory;
import utils.ExtentTestManager;
import utils.ReportManager;
import utils.ScreenshotUtil;

import java.io.IOException;

public class ExtentTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🚀 ExtentTestListener initialized!");
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver(); // From your utility
        String screenshotPath = ScreenshotUtil.takeScreenshot(result.getMethod().getMethodName());
        ExtentTestManager.getTest().fail("Test Failed: " + result.getThrowable());
        ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);

        // 🔹 Log retry attempt if RetryAnalyzer is attached
        if (result.getMethod().getRetryAnalyzer(result) instanceof RetryAnalyzer) {
            RetryAnalyzer retry = (RetryAnalyzer) result.getMethod().getRetryAnalyzer(result);
            ExtentTestManager.getTest().info("Retry attempt: " + retry.getCount());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // If skipped because retries exhausted, mark as fail instead
        if (result.getMethod().getRetryAnalyzer(result) != null) {
            ExtentTestManager.getTest().fail("Test failed after retries: " + result.getThrowable());
        } else {
            ExtentTestManager.getTest().skip("Test Skipped: " + result.getThrowable());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ReportManager.getExtent().flush();
    }
}
