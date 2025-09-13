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
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ReportManager.getExtent().flush();
    }
}
