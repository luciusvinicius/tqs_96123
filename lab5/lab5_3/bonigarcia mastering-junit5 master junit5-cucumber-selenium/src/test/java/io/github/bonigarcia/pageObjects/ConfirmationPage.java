package io.github.bonigarcia.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {

    @FindBy(tagName = "h1")
    private WebElement header;

    private WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isOpened() {
        return header.getText().contains("Thank you for your purchase today!");
    }
    
}
