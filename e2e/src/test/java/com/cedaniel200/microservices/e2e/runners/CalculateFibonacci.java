package com.cedaniel200.microservices.e2e.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/calculate_fibonacci.feature"},
        glue = {"com.cedaniel200.microservices.e2e.stepdefinitions"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class CalculateFibonacci {
}
