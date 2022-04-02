package ies.lab4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private static final String PAGE_URL = "https://blazedemo.com/";

    private WebDriver driver;

    @FindBy(name = "fromPort")
    private WebElement departure;

    @FindBy(name = "toPort")
    private WebElement destination;

    @FindBy(how = How.LINK_TEXT, using = "Find Flights")
    private WebElement findButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getFindButton() {
        return this.findButton;
    }

    public WebElement getDeparture() {
        return this.departure;
    }

    public WebElement getDestination() {
        return this.destination;
    }

    public void clickOnFindFlightsButton() {
        findButton.click();
    }

}
