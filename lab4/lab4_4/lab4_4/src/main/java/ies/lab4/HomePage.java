package ies.lab4;

import java.util.List;

import org.openqa.selenium.By;
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

    @FindBy(how = How.CSS, using = "input[class='btn btn-primary']")
    private List<WebElement> findButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void chooseOrigin(String origin) {
        departure.findElement(By.xpath("//option[. = '" + origin + "']")).click();
    }

    public void chooseDestination(String destination) {
        this.destination.findElement(By.xpath("//option[. = '" + destination + "']")).click();
    }

    public void clickOnFindFlightsButton() {
        for (WebElement btn : findButton) {
            btn.click();
        }
        // findButton.click();
    }

}
