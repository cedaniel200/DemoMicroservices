package com.cedaniel200.microservice.subtract;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SubtractAcceptanceTest {

    @LocalServerPort
    private int port;

    @Test
    void shouldSubtractIntegerNumbers() {
        when()
                .get(String.format("http://localhost:%s/subtract/?numbers=1,2,3,5,-1", port))
                .then()
                .statusCode(is(200))
                .body(containsString("-8"));
    }

}