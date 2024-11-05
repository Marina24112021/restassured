package page;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class ListResource extends TestBase {
    @Test
    void positiveCheckExistingColorsInListOfResourceTest() {
        given()
                .when()
                .log().uri()
                .get("/unknown")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.color", hasItems("#98B2D1", "#BF1932", "#53B0AE"));
    }
}
