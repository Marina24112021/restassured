package page;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class CreateUserPage extends TestBase {
    @Test
    void positiveCreateUserTest() {
        String newUser = "{\"name\": \"morpheus\",\"job\": \"leader\"}";
        given()
                .body(newUser)
                .contentType(JSON)
                .log().uri()
                .when()
                .log().uri()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void positiveCreateUserWithoutJobTest() {
        String newUser = "{\"name\": \"morpheus\",\"job\": \"\"}";
        given()
                .body(newUser)
                .contentType(JSON)
                .log().uri()
                .when()
                .log().uri()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is(""));
    }

    @Test
    void negativeCreateUserWithoutNameTest() {
        String newUser = "{\"name\": \"\",\"job\": \"leader\"}";
        given()
                .body(newUser)
                .contentType(JSON)
                .log().uri()
                .when()
                .log().uri()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", is(""))
                .body("job", is("leader"));
    }

    @Test
    void negativeCreateNullUserTest() {
        String newUser = "{\"name\": \"\",\"job\": \"\"}";
        given()
                .body(newUser)
                .contentType(JSON)
                .log().uri()
                .when()
                .log().uri()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", is(""))
                .body("job", is(""));
    }

    @Test
    void positiveCreateAnotherUserTest() {
        String newUser = "{\"name\": \"Marina Chen\",\"job\": \"QA engineer\"}";
        given()
                .body(newUser)
                .contentType(JSON)
                .log().uri()
                .when()
                .log().uri()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", is("Marina Chen"))
                .body("job", is("QA engineer"));
    }
}
