package io.cucumber.skeleton;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorTest {
    
    private Calculator calc;

    @Given("a calculator I just turned on")
    public void setup() {
        calc = new Calculator();
    }

    @When("I add {int} and {int}")
    public void add(Integer val1, Integer val2) {
        calc.push(val1);
        calc.push(val2);
        calc.push("+");
    }

    @When("I substract {int} to {int}")
    public void subtract(Integer val1, Integer val2) {
        calc.push(val1);
        calc.push(val2);
        calc.push("-");
    }

    @Then("the result is {int}")
    public void result(Integer int1) {
        System.out.println("Result is " + int1);
    }
}
