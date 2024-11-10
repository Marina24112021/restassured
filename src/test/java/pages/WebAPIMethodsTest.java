package pages;

import io.restassured.response.Response;
import modules.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static specs.WebAPIMethodsSpecs.*;

@Tag("SMOKE")
public class WebAPIMethodsTest extends TestBase {
    @Tag("POSITIVE")
    @Test
    void positiveCreateUserTest() {
        UserDataLombokModel request = new UserDataLombokModel();
        request.setName("morpheus");
        request.setJob("leader");
        UserDataResponseLombokModel userDataResponseLombokModel = step("Make request", ()->
                given(requestSpecs)
                    .body(request)

                .when()
                    .post("/users")

                .then()
                    .spec(responseSpecs201)
                    .extract().as(UserDataResponseLombokModel.class));

        step("Make response", ()-> {
            assertEquals("morpheus", userDataResponseLombokModel.getName());
            assertEquals("leader", userDataResponseLombokModel.getJob());
        });
    }
    @Tag("NEGATIVE")
    @Test
    void negativeUpdateUserPutTest() {
        UserDataLombokModel request = new UserDataLombokModel();
        request.setName("morpheus");
        request.setJob("zion resident");
        UpdateUserPurResponseModel response = step("Make request", ()->
                given(requestSpecs)
                    .body(request)

                .when()
                    .put("/users/2")

                .then()
                    .spec(responseSpecs200)
                    .extract().as(UpdateUserPurResponseModel.class));
        step("Make response", ()-> {
            assertEquals("morpheus", response.getName());
            assertEquals("zion resident", response.getJob());
        });
    }
    @Tag("POSITIVE")
    @Test
    void positiveDeleteUserTest() {
        given(requestSpecs)
                .when()
                    .delete("/users/2")

                .then()
                    .spec(responseSpecs204);
    }
    @Tag("POSITIVE")
    @Test
    void positiveRegisterUserTest() {
        RegistrationPostRequestModel request = new RegistrationPostRequestModel();
        request.setEmail("eve.holt@reqres.in");
        request.setPassword("pistol");
        RegistrationUserPostResponseModel response = step("Make request", ()->
                given(requestSpecs)
                    .body(request)

                .when()
                    .post("/register")

                .then()
                    .spec(responseSpecs200)
                    .extract().as(RegistrationUserPostResponseModel.class));
        step("Make response", ()->
            assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
    }
    @Tag("POSITIVE")
    @Test
    void positiveCheckExistingColorsInListOfResourceTest() {
        Response response = step("Make request", ()->
                given(requestSpecs)
                .when()
                    .get("/unknown")

                .then()
                    .spec(responseSpecs200)
                    .extract().response());
                List<String> actualColors = response.path("data.color");
                List<String> expectedColors = Arrays.asList("#98B2D1", "#BF1932", "#53B0AE");
        step("Make response", ()->
                assertTrue(actualColors.containsAll(expectedColors)));
    }
}
