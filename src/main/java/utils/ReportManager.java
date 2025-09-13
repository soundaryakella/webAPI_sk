package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ReportManager {
    private static ExtentReports extent;

    public static ExtentReports getReporter() {
        if (extent == null) {
            // Create folder for reports
            String reportFolder = System.getProperty("user.dir") + File.separator + "reports";
            new File(reportFolder).mkdirs();

            // Create spark reporter with file path inside the folder
            String reportPath = reportFolder + File.separator + "extent-report.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

            // Configurations
            spark.config().setReportName("Web Automation Report");
            spark.config().setDocumentTitle("Test Execution Results");

            // Extent object
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Tester", "Soundarya");
        }
        return extent;
    }

    public static ExtentReports getExtent() {
        return extent;
    }
}
