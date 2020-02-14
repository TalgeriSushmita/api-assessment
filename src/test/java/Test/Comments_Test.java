package Test;

import Config.BaseConfig;
import Config.Endpoints;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.HashSet;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class Comments_Test extends BaseConfig {

    /*
    This is a test with path param and to retrieve unique set of email id's from the JSON response
     */

    @Test
    public void GetComments() {
        Response response =
                given()
                        .spec(requestSpecification)
                        .pathParam("postId", 3)
                        .log().all()

                        .when()
                        .get(Endpoints.POST_COMMENTS_ON_ID)

                        .then()
                        .assertThat()
                        .spec(responseSpecification)

                        .body("id.size()", equalTo(5))
                        .body("id[0]", equalTo(11))

                        .extract().response();

        HashSet temp_ = new HashSet(response.jsonPath().get("email"));
        System.out.println("Unique set of email id's retrieved from JSON response " + temp_);
    }
}
