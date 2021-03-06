// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class Api1Test {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void api1() {
    driver.get("http://localhost:3000/");
    driver.manage().window().setSize(new Dimension(640, 752));
    assertTrue(driver.findElement(By.name("row-radio-buttons-group")).isSelected());
    driver.findElement(By.id(":r3:")).click();
    driver.findElement(By.id(":r3:")).sendKeys("brazil");
    driver.findElement(By.id(":r3:")).sendKeys(Keys.DOWN);
    driver.findElement(By.id(":r3:")).sendKeys(Keys.ENTER);
    {
      WebElement element = driver.findElement(By.cssSelector(".MuiFormControl-root:nth-child(3) path"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    driver.findElement(By.cssSelector(".MuiFormControl-root:nth-child(3) path")).click();
    {
      WebElement element = driver.findElement(By.tagName("body"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element, 0, 0).perform();
    }
    driver.findElement(By.cssSelector(".css-mvmu1r:nth-child(3) > div:nth-child(3) > .MuiButtonBase-root")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".MuiFormControl-root:nth-child(4) .MuiSvgIcon-root"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    driver.findElement(By.cssSelector(".MuiFormControl-root:nth-child(4) .MuiSvgIcon-root")).click();
    {
      WebElement element = driver.findElement(By.tagName("body"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element, 0, 0).perform();
    }
    driver.findElement(By.cssSelector(".css-mvmu1r:nth-child(3) > div:nth-child(5) > .MuiButtonBase-root")).click();
    assertThat(driver.findElement(By.cssSelector("p:nth-child(9)")).getText(), is("Country: Brazil"));
    assertThat(driver.findElement(By.cssSelector("h2:nth-child(8)")).getText(), is("Covid Data for 2022-04-12"));
    assertThat(driver.findElement(By.cssSelector("h2:nth-child(17)")).getText(), is("Covid Data for 2022-04-13"));
    assertThat(driver.findElement(By.cssSelector("h2:nth-child(26)")).getText(), is("Covid Data for 2022-04-14"));
  }
}
