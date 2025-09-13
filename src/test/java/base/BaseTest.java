package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.DriverFactory;
import utils.ReportManager;

import java.lang.reflect.Method;

public class BaseTest {

    protected ExtentReports extent;
    protected ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeSuite
    public void setupReport() {
        extent = ReportManager.getReporter();
    }

    @BeforeMethod
    public void setup(Method method) {
        // Initialize WebDriver
        DriverFactory.getDriver();

        // Create a new ExtentTest instance per thread
//        test.set(extent.createTest(method.getName()));

        // Optional debug
        System.out.println("Thread ID: " + Thread.currentThread().getId() +
                " - Driver hash: " + DriverFactory.getDriver().hashCode());
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
        test.remove(); // Clean up thread-local ExtentTest
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush(); // Only flush once after all tests
    }

    // Optional getter for ExtentTest per thread
    protected ExtentTest getTest() {
        return test.get();
    }
}
