package com.example.microservice.e2e.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CalculateFibonacci implements Task {

    private static final String RESOURSE_CALCULATE_FIBONACCI = "/fibonacci?number=%d";
    private static final String RESOURSE_CALCULATE_FIBONACCI_RANDOM = "/fibonacci/random";
    private String resource;

    public CalculateFibonacci(String resource) {
        this.resource = resource;
    }

    public static CalculateFibonacci of(int number) {
        return instrumented(CalculateFibonacci.class, String.format(RESOURSE_CALCULATE_FIBONACCI, number));
    }

    public static CalculateFibonacci ofRandomNumber() {
        return instrumented(CalculateFibonacci.class,RESOURSE_CALCULATE_FIBONACCI_RANDOM);
    }

    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(
                Get.resource(this.resource)
        );
    }
}
