package com.example.microservices.e2e.stepdefinitions;

import com.example.microservices.e2e.questions.LastResponseStatusCode;
import com.example.microservices.e2e.questions.Result;
import com.example.microservices.e2e.tasks.CalculateFibonacci;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.core.Is.is;

public class CalculateFibonacciStepDefinitions {

    private static final String CESAR = "Cesar";

    @Before
    public void prepareStage() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled(CESAR).whoCan(CallAnApi.at("http://localhost:8762/api/calculator"));
    }

    @When("^Cesar calculates the fibonacci of (\\d+)$")
    public void cesarCalculatesTheFibonacciOf(int number) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                CalculateFibonacci.of(number)
        );
    }

    @Then("^Cesar should see that the result is (\\d+)$")
    public void cesarShouldSeeThatTheResultIs(int result) {
        OnStage.theActorInTheSpotlight().should(seeThat("last response status code is 200",
                LastResponseStatusCode.is(200)));
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat("the result of fibonacci",
                Result.fibonacci(), is(result)));
    }

    @When("^Cesar calculates the fibonacci of a random number$")
    public void cesarCalculatesTheFibonacciOfARandomNumber() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                CalculateFibonacci.ofRandomNumber()
        );
    }

    @Then("^Cesar should see that the result is correct$")
    public void cesarShouldSeeThatTheResultIsCorrect() {
        OnStage.theActorInTheSpotlight().should(seeThat("last response status code is 200",
                LastResponseStatusCode.is(200)));
        OnStage.theActorInTheSpotlight().should(seeThat("the result of fibonacci random is correct",
                Result.fibonacciRandomIsCorrect()));
    }
}
