package io.github.bonigarcia.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ReservePage {

    private static final String PAGE_URL = "https://blazedemo.com/reserve.php";

    private WebDriver driver;

    @FindBy(how = How.CSS, using = "input[class='btn btn-small']")
    private List<WebElement> chooseFlightButtons;

    @FindBy(tagName = "h3")
    private WebElement header;
    
    public ReservePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnFlight(int n) {
        chooseFlightButtons.get(n).click();
    }

    public boolean isOpened() {
        return header.getText().contains("Flights from");
    }

}
