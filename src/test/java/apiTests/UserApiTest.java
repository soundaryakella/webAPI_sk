package apiTests;

import apiPojo.RegisterUserRequest;
import apiPojo.RegisterUserResponse;
import base.ApiClient;
import base.BaseTest;
import dataprovider.jsonTestData.UserDataProvider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserApiTest extends BaseTest {

    @Test(dataProvider = "registerUserData", dataProviderClass = UserDataProvider.class)
    public void registerUserSuccessfully(Map<String, String> userData) {
        Response response = given()
                .when()
                .get("https://practice.expandtesting.com/")
                .then()
                .extract().response();

        Map<String, String> cookies = response.getCookies();

        System.out.println(cookies);
        RegisterUserRequest request = new RegisterUserRequest();
        request.setName(userData.get("name"));
        request.setEmail(userData.get("email"));
        request.setPassword(userData.get("password"));

        Response registerUserResponse = given()
                .spec(ApiClient.getRequest())
                .cookies(cookies)
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .accept("application/json")
                .formParam("name", request.getName())
                .formParam("email", request.getEmail())
                .formParam("password", request.getPassword())
                .when()
                .log().all()
                .post("/notes/api/users/register")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .extract().response();

        RegisterUserResponse responseBody = registerUserResponse.as(RegisterUserResponse.class);
        Assert.assertTrue(responseBody.isSuccess());
        Assert.assertEquals(responseBody.getStatus(), 201);
        Assert.assertEquals(responseBody.getMessage(), "User account created successfully");
        Assert.assertEquals(responseBody.getData().getName(), request.getName());
        String id = responseBody.getData().getId();
        System.out.println("id :-"+id);
        Assert.assertEquals(responseBody.getData().getEmail(), request.getEmail());
    }
}
