package utils;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void startTest(String testName) {
        ExtentTest extentTest = ReportManager.getExtent().createTest(testName);
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }
}
