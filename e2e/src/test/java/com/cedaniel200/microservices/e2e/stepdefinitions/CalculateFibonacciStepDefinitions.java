package com.cedaniel200.microservices.e2e.stepdefinitions;

import com.cedaniel200.microservices.e2e.questions.LastResponseStatusCode;
import com.cedaniel200.microservices.e2e.questions.Result;
import com.cedaniel200.microservices.e2e.tasks.CalculateFibonacci;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.core.Is.is;

public class CalculateFibonacciStepDefinitions {

    private static final String CESAR = "Cesar";

    @Before
    public void prepareStage() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled(CESAR).whoCan(CallAnApi.at("http://localhost:8762"));
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
        OnStage.theActorInTheSpotlight().should(seeThat("the result of fibonacci",
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
