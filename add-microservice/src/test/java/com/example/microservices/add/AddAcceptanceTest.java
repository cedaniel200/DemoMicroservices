package com.example.microservices.add;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddAcceptanceTest {

    @LocalServerPort
    private int port;

    @Test
    public void shouldReturnYesterdaysWeather() {
        when()
                .get(String.format("http://localhost:%s/?numbers=1,2,3,5,-1", port))
                .then()
                .statusCode(is(200))
                .body(containsString("10"));
    }
}
