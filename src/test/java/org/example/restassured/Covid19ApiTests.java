package org.example.restassured;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class Covid19ApiTests {

    @Test
    public void testingCovid19ApiSummary() {
        get("https://api.covid19api.com/summary")
                .then().body(matchesJsonSchemaInClasspath("org/example/restassured/covid19api_summary.json"));
    }
}
