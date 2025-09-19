package listeners;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private final int maxRetry = 1; // retry once

    @Override
    public boolean retry(ITestResult result) {
        if (count < maxRetry) {
            count++;
            System.out.println("Retrying " + result.getName() + " again, attempt " + count);
            return true; // will re-run the test
        }
        return false; // stop retrying
    }

    public int getCount() {
        return count; // so Listener can log retry attempts in ExtentReports
    }
}