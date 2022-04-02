package ies.lab4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchasePage {

    private static final String PAGE_URL = "https://blazedemo.com/purchase.php";
    
    private WebDriver driver;

    @FindBy(id = "inputName")
    private WebElement name;

    @FindBy(id = "address")
    private WebElement address;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "state")
    private WebElement state;

    @FindBy(id = "zipCode")
    private WebElement zipCode;

    @FindBy(id = "cardType")
    private WebElement cardType;

    @FindBy(id = "creditCardNumber")
    private WebElement creditCardNumber;

    @FindBy(id = "creditCardMonth")
    private WebElement creditCardMonth;

    @FindBy(id = "creditCardYear")
    private WebElement creditCardYear;

    @FindBy(id = "nameOnCard")
    private WebElement nameOnCard;

    @FindBy(className = "checkbox")
    private WebElement rememberMeCheckbox;

    @FindBy(className = "btn btn-primary")
    private WebElement purchaseButton;

    public PurchasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setName(String name) {
        this.name.clear();
        this.name.sendKeys(name);
    }

    public void setAddress(String address) {
        this.address.clear();
        this.address.sendKeys(address);
    }

    public void setCity(String city) {
        this.city.clear();
        this.city.sendKeys(city);
    }

    public void setState(String state) {
        this.state.clear();
        this.state.sendKeys(state);
    }

    public void setZipCode(String zipCode) {
        this.zipCode.clear();
        this.zipCode.sendKeys(zipCode);
    }

    // public void setCardType(String cardType) {
    //     this.cardType.clear();
    //     this.cardType.sendKeys(cardType);
    // }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber.clear();
        this.creditCardNumber.sendKeys(creditCardNumber);
    }

    public void setCreditCardMonth(String creditCardMonth) {
        this.creditCardMonth.clear();
        this.creditCardMonth.sendKeys(creditCardMonth);
    }

    public void setCreditCardYear(String creditCardYear) {
        this.creditCardYear.clear();
        this.creditCardYear.sendKeys(creditCardYear);
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard.clear();
        this.nameOnCard.sendKeys(nameOnCard);
    }

    

    
}
