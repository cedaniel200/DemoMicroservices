package com.cedaniel200.microservices.add;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddAcceptanceTest {

    @LocalServerPort
    private int port;

    @Test
    public void shouldAddIntegerNumbers() {
        when()
                .get(String.format("http://localhost:%s/add/?numbers=1,2,3,5,-1", port))
                .then()
                .statusCode(is(200))
                .body(containsString("10"));
    }
}
