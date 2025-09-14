package dataprovider;

import org.testng.annotations.DataProvider;

import java.util.Map;

/**
 * Provides login user data for UI tests.
 * Each data set is a Map<String, String> with keys: "username" and "password".
 */
public class LoginPageDataProvider {

    private static final String USER_NAME = "username";
    private static final String PASSWORD = "password";

    /**
     * Provides test data for login functionality.
     * Runs in parallel safely using immutable maps.
     *
     * @return Object[][] where each Object[] contains a Map<String, String> of user credentials.
     */
    @DataProvider(name = "loginUserData", parallel = true)
    public static Object[][] loginTestData() {
        return new Object[][]{
                {Map.of(USER_NAME, "student",          PASSWORD, "Password123")},
                {Map.of(USER_NAME, "student",  PASSWORD, "Password1234")}
        };
    }
}
