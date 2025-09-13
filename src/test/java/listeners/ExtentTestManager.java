//package listeners;
//
//import com.aventstack.extentreports.ExtentTest;
//import org.testng.ITestResult;
//import utils.ReportManager;
//
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * Thread-safe ExtentTest manager that handles retries properly
// * Prevents duplicate test entries in Extent Reports
// */
//public class ExtentTestManager {
//    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//    private static final ConcurrentHashMap<String, ExtentTest> testMap = new ConcurrentHashMap<>();
//
//    /**
//     * Start test with retry awareness
//     * Only creates new test if it doesn't exist or if it's a retry
//     */
//    public static void startTest(String testName) {
//        startTest(testName, null);
//    }
//
//    /**
//     * Start test with retry awareness and test result
//     */
//    public static void startTest(String testName, ITestResult result) {
//        String uniqueTestKey = getUniqueTestKey(testName, result);
//
//        // Check if test already exists (for retries)
//        if (testMap.containsKey(uniqueTestKey)) {
//            test.set(testMap.get(uniqueTestKey));
//        } else {
//            // Create new test only if it doesn't exist
//            ExtentTest extentTest = ReportManager.getExtent().createTest(testName);
//            test.set(extentTest);
//            testMap.put(uniqueTestKey, extentTest);
//        }
//    }
//
//    /**
//     * Get current test instance
//     */
//    public static ExtentTest getTest() {
//        return test.get();
//    }
//
//    /**
//     * Get unique test key for retry handling
//     */
//    private static String getUniqueTestKey(String testName, ITestResult result) {
//        if (result != null) {
//            // Use method signature and instance hash for uniqueness
//            return testName + "_" + result.getMethod().getMethod().getDeclaringClass().getSimpleName() +
//                   "_" + result.getInstance().hashCode();
//        }
//        return testName + "_" + Thread.currentThread().getId();
//    }
//
//    /**
//     * End test and clean up
//     */
//    public static void endTest() {
//        test.remove();
//    }
//
//    /**
//     * Clean up test map (call in @AfterSuite)
//     */
//    public static void cleanup() {
//        testMap.clear();
//    }
//
//    /**
//     * Get test count for reporting
//     */
//    public static int getTestCount() {
//        return testMap.size();
//    }
//}
