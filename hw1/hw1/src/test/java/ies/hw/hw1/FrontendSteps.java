package ies.hw.hw1;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


public class FrontendSteps {
    
    private WebDriver driver;
    private String selectedApi = "";

    JavascriptExecutor js;
    @Given("the website just opened")
    public void setUp() {
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;

        driver.get("http://localhost:3000/");
    }

    @When("I choose for the {string} API")
    public void chooseApi(String api) {
        selectedApi = api;
        if (api.equals("Api 1")) {
            assertTrue(driver.findElement(By.name("row-radio-buttons-group")).isSelected());
        }
        else if (api.equals("Api 2")) {
            {
                WebElement element = driver.findElement(By.cssSelector(".MuiFormControlLabel-root:nth-child(2) .PrivateSwitchBase-input"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element).perform();
            }
            driver.findElement(By.cssSelector(".MuiFormControlLabel-root:nth-child(2) .PrivateSwitchBase-input")).click();
            assertTrue(driver.findElement(By.cssSelector(".MuiFormControlLabel-root:nth-child(2) .PrivateSwitchBase-input")).isSelected());
        }
        System.out.println("You choosed api: " + api);

    }

    @And("I choose the country {string}")
    public void chooseCountry(String country) {
        System.out.println("Country: " + country);
        String name_id = selectedApi.equals("Api 1") ? ":r3:" : ":rb:";
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(visibilityOfElementLocated(By.id(name_id)));

        driver.findElement(By.id(name_id)).click();
        driver.findElement(By.id(name_id)).sendKeys(country);
        driver.findElement(By.id(name_id)).sendKeys(Keys.DOWN);
        driver.findElement(By.id(name_id)).sendKeys(Keys.ENTER);
    }

    @And("I choose the date range between 12 and 18 of April")
    public void setDates() {

        driver.findElement(By.cssSelector(".MuiFormControl-root:nth-child(3) .MuiSvgIcon-root")).click();
        driver.findElement(By.cssSelector(".css-mvmu1r:nth-child(3) > div:nth-child(3) > .MuiButtonBase-root")).click();
        driver.findElement(By.cssSelector(".MuiFormControl-root:nth-child(4) .MuiSvgIcon-root")).click();
        driver.findElement(By.cssSelector(".css-mvmu1r:nth-child(4) > div:nth-child(2) > .MuiButtonBase-root")).click();
    }

    @Then("new deaths on day {int} is {int}")
    public void assertDeaths(int day, int newDeaths) {
        int scrollDistance = 25;
        int h2Position = 9 * (day - 11) - 1;
        int newDeathsDistance = h2Position + 7;
        String cssSelector = "h2:nth-child(" + h2Position + ")" ;
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(visibilityOfElementLocated(By.cssSelector(cssSelector)));

        {
            WebElement element = driver.findElement(By.cssSelector(cssSelector));
            Actions builder = new Actions(driver);

            // If out of range, scroll until it reaches it
            while (true) {
                try {
                    builder.moveToElement(element).perform();
                    break;
                }
                catch (MoveTargetOutOfBoundsException e) {
                    js.executeScript("scroll(0," + scrollDistance + ")");
                    scrollDistance *= 2;
                }
            }
        }
        assertThat(driver.findElement(By.cssSelector(cssSelector))
                .getText(), is("Covid Data for 2022-04-"+day));

        assertThat(driver.findElement(By.cssSelector("p:nth-child(" + newDeathsDistance + ")"))
                .getText(), is("New Deaths: " + newDeaths));

        driver.quit();
    }
}
