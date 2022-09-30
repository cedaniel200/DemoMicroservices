Feature: Calculate Fibonacci
  Cesar want to Calculate Fibonacci to fun

  Scenario: Calculate Fibonacci of 10
    When Cesar calculates the fibonacci of 10
    Then Cesar should see that the result is 55

  Scenario: Calculate Fibonacci of a random number
    When Cesar calculates the fibonacci of a random number
    Then Cesar should see that the result is correct