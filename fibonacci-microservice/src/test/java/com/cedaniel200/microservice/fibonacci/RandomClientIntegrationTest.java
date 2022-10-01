package com.cedaniel200.microservice.fibonacci;

import com.cedaniel200.microservice.fibonacci.domain.FibonacciCalculatorDefault;
import com.cedaniel200.microservice.fibonacci.model.Fibonacci;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@SpringBootTest
class RandomClientIntegrationTest {

    @Autowired
    private FibonacciCalculatorDefault fibonacciCalculator;
    WireMockServer wireMockServer;

    @BeforeEach
    public void setup () {
        wireMockServer = new WireMockServer(9000);
        wireMockServer.start();
    }

    @Test
    void shouldCallRandomService() throws Exception {
        wireMockServer.stubFor(get(urlPathEqualTo("/random"))
                .willReturn(aResponse()
                        .withBody("{\"value\":8}")
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));
        Fibonacci expectedResponse = new Fibonacci(8, 21);

        Fibonacci fibonacci = fibonacciCalculator.calculateFibonacciOfARandomNumber();

        assertThat(fibonacci.getResult(), is(expectedResponse.getResult()));
    }

    @AfterEach
    void down(){
        wireMockServer.stop();
    }
}
