package com.example.microservice.fibonacci;

import com.example.microservice.fibonacci.domain.FibonacciCalculatorDefault;
import com.example.microservice.fibonacci.model.Fibonacci;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomClientIntegrationTest {

    @Autowired
    private FibonacciCalculatorDefault fibonacciCalculator;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(9000);

    @Test
    public void shouldCallRandomService() throws Exception {
        wireMockRule.stubFor(get(urlPathEqualTo("/random"))
                .willReturn(aResponse()
                        .withBody("{\"value\":8}")
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));
        Fibonacci expectedResponse = new Fibonacci(8, 21);

        Fibonacci fibonacci = fibonacciCalculator.calculateFibonacciOfARandomNumber();

        assertThat(fibonacci.getResult(), is(expectedResponse.getResult()));
    }
}
