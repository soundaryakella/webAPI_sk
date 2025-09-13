package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ReportManager {
    private static ExtentReports extent;

    public static ExtentReports getReporter() {
        if (extent == null) {
            String folderPath = System.getProperty("user.dir") + File.separator + "reports/extent.html";
            File reportsDir = new File(folderPath);
            ExtentSparkReporter spark = new ExtentSparkReporter(reportsDir);
            spark.config().setReportName("Web Automation");
            spark.config().setDocumentTitle("Web Automation Reports");
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Tester","Soundarya");
            return extent;
        }
        return extent;
    }

    public static ExtentReports getExtent() {
        return extent;
    }
}
