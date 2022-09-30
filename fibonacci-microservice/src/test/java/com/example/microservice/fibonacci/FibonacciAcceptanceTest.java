package com.example.microservice.fibonacci;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FibonacciAcceptanceTest {

    @LocalServerPort
    private int port;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(9000);

    @Test
    public void shouldCalculateFibonacciSeriesOfZero() {
        when()
                .get(String.format("http://localhost:%s/?number=0", port))
                .then()
                .statusCode(is(200))
                .body(containsString("0"));
    }

    @Test
    public void shouldCalculateFibonacciSeriesOfOne() {
        when()
                .get(String.format("http://localhost:%s/?number=1", port))
                .then()
                .statusCode(is(200))
                .body(containsString("1"));
    }

    @Test
    public void shouldCalculateFibonacciSeriesOfEight() {
        when()
                .get(String.format("http://localhost:%s/?number=8", port))
                .then()
                .statusCode(is(200))
                .body(containsString("21"));
    }

    @Test
    public void shouldNotCalculateFibonacciSeriesOfTwelve() {
        when()
                .get(String.format("http://localhost:%s/?number=12", port))
                .then()
                .statusCode(is(200))
                .body(containsString("-1"));
    }

    @Test
    public void shouldNotCalculateFibonacciSeriesOfMinusTwo() {
        when()
                .get(String.format("http://localhost:%s/?number=-2", port))
                .then()
                .statusCode(is(200))
                .body(containsString("-1"));
    }

    @Test
    public void shouldCalculateFibonacciSeriesOfRandomNumber() {
        wireMockRule.stubFor(get(urlPathEqualTo("/random"))
                .willReturn(aResponse()
                        .withBody("{\"value\":7}")
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));

        when()
                .get(String.format("http://localhost:%s/random", port))
                .then()
                .statusCode(is(200))
                .body(containsString("13"));
    }
}
