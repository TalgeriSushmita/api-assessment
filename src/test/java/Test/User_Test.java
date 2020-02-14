package Test;

import Config.BaseConfig;
import Config.Endpoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

    /*
    This is a test to validate response data
    */


public class User_Test extends BaseConfig {

    @Test
    public void getUsers() {
        Response response =
                given()
                        .spec(requestSpecification)

                        .when()
                        .get(Endpoints.GET_USERS)

                        .then()
                        .spec(responseSpecification)

                        .body("email[0]", equalTo("Sincere@april.biz"))
//                        .body("find { it.id == 1 }.address.street", hasItem("Kulas Light"))

                        .extract().response();
        JsonPath jsonPathValidator = response.jsonPath();

        long timeInSeconds = get(Endpoints.GET_USERS).getTime();
        System.out.println("time for the response in micro second " + timeInSeconds);

        List<String> users = jsonPathValidator.getList("id");
        System.out.println("Number of Users : " + users.size());

        List<String> names = jsonPathValidator.getList("name");
        System.out.println("Names of the users");
        for(String eachName: names) {
            System.out.println(eachName);
        }


    }
}
