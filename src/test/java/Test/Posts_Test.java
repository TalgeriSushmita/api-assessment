package Test;

import Config.BaseConfig;
import Config.Endpoints;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class Posts_Test extends BaseConfig {

    /*
    This is a test to validate GET request's json response and header validation & Post request
    */

    @Test
    public void VerifyPostRequestAndHeader() {
        Response response =
                given()
                        .spec(requestSpecification)
                        .log().all()

                        .when()
                        .get(Endpoints.POST)

                        .then()
                        .assertThat()
                        .spec(responseSpecification)

                        .body("id.size()", equalTo(100))
                        .body("userId[0]", equalTo(1))

                        .extract().response();
        String jsonResponse = response.asString();
        System.out.println(jsonResponse);

        Headers headers = response.getHeaders();
        Assert.assertNotNull(headers.get("Date"));
    }


    //    TODO You will see the below test failing , it fails with status code 500 when you POST to the endpoint
    @Test
    public void AddPosts() {
        String newPostBody = "{\n" +
                "  \"id\": 101,\n" +
                "  \"title\": \"New Post\",\n" +
                "  \"body\": \"Hi There !\",\n" +
                "  \"userId\": 1,\n" +
                "}";

        given()
                .spec(requestSpecification)
                .log().all()

                .when()
                .body(newPostBody)
                .post(Endpoints.POST)

                .then()
                .assertThat()
                .statusCode(201);
    }
}
