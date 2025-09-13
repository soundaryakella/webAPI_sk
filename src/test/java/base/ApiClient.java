package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;

public class ApiClient {
    public static RequestSpecification getRequest() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.get("api.baseUrl"))
                .setContentType(ContentType.JSON)
                .build();
    }

}
