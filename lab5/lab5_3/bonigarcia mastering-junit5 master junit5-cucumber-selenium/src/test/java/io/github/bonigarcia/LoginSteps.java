/*
 * (C) Copyright 2020 Boni Garcia (https://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.bonigarcia;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.pageObjects.ConfirmationPage;
import io.github.bonigarcia.pageObjects.HomePage;
import io.github.bonigarcia.pageObjects.PurchasePage;
import io.github.bonigarcia.pageObjects.ReservePage;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private WebDriver driver;

    private HomePage homePage;
    private ReservePage reservePage;
    private PurchasePage purchasePage;
    private ConfirmationPage confirmationPage;

    @When("I start a reserve")
    public void iStart() {
        driver = WebDriverManager.chromedriver().create();
        driver.get(HomePage.getUrl());
        homePage = new HomePage(driver);
    }

    @And("I choose {string} as my departure")
    public void iChooseDeparture(String origin) {
        homePage.chooseOrigin(origin);
    }

    @And("I choose {string} as my destination")
    public void iChooseDestination(String destination) {
        homePage.chooseDestination(destination);
    } 

    @And("I click Find Flights")
    public void iClickFindFlights() {
        homePage.clickOnFindFlightsButton();
    }

    @Then("Reserve Page should be opened") 
    public void iReserveShouldBeOpened() {
        reservePage = new ReservePage(driver);
        assertTrue(reservePage.isOpened());
    }

    @And("I click on the flight button number {int}")
    public void iClickFlightButton(Integer idx) {
        reservePage.clickOnFlight(idx);
        purchasePage = new PurchasePage(driver);
    }

    @Then("Purchase Page should be opened")
    public void iPurchaseShouldBeOpened() {
        assertTrue(purchasePage.isOpened());
    }

    @And("I insert {string} as my name, {string} as my address, {string} as my city, {string} as my state, {int} as my zip code, {string} as my card type, {int} as my credit card number, {int} as month, {int} as year, {string} as my name on card")
    public void iFillForm(String name, String address, String city, String state, Integer zip, 
    String cardType, Integer cardNumber, Integer month, Integer year, String cardName) {

        purchasePage.setName(name);
        purchasePage.setAddress(address);
        purchasePage.setCity(city);
        purchasePage.setState(state);
        purchasePage.setZipCode(zip + "");
        purchasePage.setCardType(cardType); // TODO
        purchasePage.setCreditCardNumber(cardNumber + "");
        purchasePage.setCreditCardMonth(month + "");
        purchasePage.setCreditCardYear(year + "");
        purchasePage.setNameOnCard(cardName);
    }

    @And("I click on remember me checkbox")
    public void iRememberMeCheckbox() {
        purchasePage.rememberMeCheck();
    }

    @And("I click on Purchase Flight")
    public void iClickPurchaseFlight() {
        purchasePage.purchaseButtonClick();
        confirmationPage = new ConfirmationPage(driver);
    }

    @Then("Confirmation Page should be opened")
    public void iConfimationShouldBeOpened() {
        assertTrue(confirmationPage.isOpened());
    }

    // @When("I navigate to {string}")
    // public void iNavigateTo(String url) {
    //     driver = WebDriverManager.chromedriver().create();
    //     driver.get(url);
    // }

    // @And("I login with the username {string} and password {string}")
    // public void iLogin(String username, String password) {
    //     driver.findElement(By.id("username")).sendKeys(username);
    //     driver.findElement(By.id("password")).sendKeys(password);

    // }

    // @And("I click Submit")
    // public void iPressEnter() {
    //     driver.findElement(By.cssSelector("button")).click();
    // }

    // @Then("I should be see the message {string}")
    // public void iShouldSee(String result) {
    //     try {
    //         driver.findElement(
    //                 By.xpath("//*[contains(text(), '" + result + "')]"));
    //     } catch (NoSuchElementException e) {
    //         throw new AssertionError(
    //                 "\"" + result + "\" not available in results");
    //     } finally {
    //         driver.quit();
    //     }
    // }

}
