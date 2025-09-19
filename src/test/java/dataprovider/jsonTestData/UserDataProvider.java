package dataprovider.jsonTestData;

import org.testng.annotations.DataProvider;

import java.util.Map;

public class UserDataProvider {
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    @DataProvider(name = "registerUserData", parallel = true)
    public static Object[][] registerUserData() {
        return new Object[][]{
                { Map.of(NAME, "soundhu22", EMAIL, "soundarya22@gmail.com", PASSWORD, "12345@") },
                { Map.of(NAME, "soundhu33", EMAIL, "soundarya33@gmail.com", PASSWORD, "abcde@123") },
                { Map.of(NAME, "soundhu44", EMAIL, "soundarya44@gmail.com", PASSWORD, "P@ssword1") }
        };
    }
}
