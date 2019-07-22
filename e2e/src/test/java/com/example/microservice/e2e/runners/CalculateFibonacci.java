package com.example.microservice.e2e.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/calculate_fibonacci.feature"},
        glue = {"com.example.microservices.e2e.stepdefinitions"},
        snippets = SnippetType.CAMELCASE
)
public class CalculateFibonacci {
}
