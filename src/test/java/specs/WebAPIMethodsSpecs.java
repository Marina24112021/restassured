package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class WebAPIMethodsSpecs {
    public static RequestSpecification requestSpecs = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON);
    public static ResponseSpecification responseSpecs200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();
    public static ResponseSpecification responseSpecs201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(ALL)
            .build();
    public static ResponseSpecification responseSpecs204 = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(ALL)
            .build();
}
