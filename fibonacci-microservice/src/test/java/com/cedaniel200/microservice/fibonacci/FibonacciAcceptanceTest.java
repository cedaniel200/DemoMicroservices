package com.cedaniel200.microservice.fibonacci;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FibonacciAcceptanceTest {

    @LocalServerPort
    private int port;
    private WireMockServer wireMockServer;

    @BeforeEach
    void setup() {
        wireMockServer = new WireMockServer(9000);
        wireMockServer.start();
    }

    @Test
    void shouldCalculateFibonacciSeriesOfZero() {
        when()
                .get(String.format("http://localhost:%s/fibonacci/?number=0", port))
                .then()
                .statusCode(is(200))
                .body(containsString("0"));
    }

    @Test
    void shouldCalculateFibonacciSeriesOfOne() {
        when()
                .get(String.format("http://localhost:%s/fibonacci/?number=1", port))
                .then()
                .statusCode(is(200))
                .body(containsString("1"));
    }

    @Test
    void shouldCalculateFibonacciSeriesOfEight() {
        when()
                .get(String.format("http://localhost:%s/fibonacci/?number=8", port))
                .then()
                .statusCode(is(200))
                .body(containsString("21"));
    }

    @Test
    void shouldNotCalculateFibonacciSeriesOfTwelve() {
        when()
                .get(String.format("http://localhost:%s/fibonacci/?number=12", port))
                .then()
                .statusCode(is(200))
                .body(containsString("-1"));
    }

    @Test
    void shouldNotCalculateFibonacciSeriesOfMinusTwo() {
        when()
                .get(String.format("http://localhost:%s/fibonacci/?number=-2", port))
                .then()
                .statusCode(is(200))
                .body(containsString("-1"));
    }

    @Test
    void shouldCalculateFibonacciSeriesOfRandomNumber() {
        wireMockServer.stubFor(get(urlPathEqualTo("/random"))
                .willReturn(aResponse()
                        .withBody("{\"value\":7}")
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));

        when()
                .get(String.format("http://localhost:%s/fibonacci/random", port))
                .then()
                .statusCode(is(200))
                .body(containsString("13"));
    }

    @AfterEach
    void down() {
        wireMockServer.stop();
    }
}
