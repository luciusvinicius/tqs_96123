// // Generated by Selenium IDE
// package ies.lab4;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// // import static org.junit.Assert.*;
// import static org.hamcrest.CoreMatchers.is;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.hamcrest.MatcherAssert.assertThat;

// import org.openqa.selenium.By;
// import org.openqa.selenium.firefox.FirefoxDriver;
// import org.openqa.selenium.Dimension;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.JavascriptExecutor;

// import io.github.bonigarcia.seljup.SeleniumJupiter;

// @ExtendWith(SeleniumJupiter.class)
// public class SeleniumIDETest {
//   // private WebDriver driver;
//   // private Map<String, Object> vars;
//   // JavascriptExecutor js;

//   @Test
//   public void test(FirefoxDriver driver) {

//     // driver = new FirefoxDriver();
//     // js = (JavascriptExecutor) driver;
//     // vars = new HashMap<String, Object>();

//     driver.get("https://blazedemo.com/");
//     driver.manage().window().setSize(new Dimension(550, 691));
//     driver.findElement(By.cssSelector("form")).click();
//     driver.findElement(By.name("fromPort")).click();
//     {
//       WebElement dropdown = driver.findElement(By.name("fromPort"));
//       dropdown.findElement(By.xpath("//option[. = 'Philadelphia']")).click();
//     }
//     driver.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(2)")).click();
//     driver.findElement(By.name("toPort")).click();
//     {
//       WebElement dropdown = driver.findElement(By.name("toPort"));
//       dropdown.findElement(By.xpath("//option[. = 'New York']")).click();
//     }
//     driver.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(5)")).click();
//     driver.findElement(By.cssSelector(".btn-primary")).click();
//     driver.findElement(By.cssSelector("tr:nth-child(2) .btn")).click();
//     driver.findElement(By.id("inputName")).click();
//     driver.findElement(By.id("inputName")).sendKeys("Sussy boy");
//     driver.findElement(By.id("address")).click();
//     driver.findElement(By.id("address")).sendKeys("123 main st.");
//     driver.findElement(By.id("city")).click();
//     driver.findElement(By.id("city")).sendKeys("That one");
//     driver.findElement(By.cssSelector(".control-group:nth-child(5) > .controls")).click();
//     driver.findElement(By.id("state")).click();
//     driver.findElement(By.id("state")).sendKeys("Goias");
//     driver.findElement(By.id("zipCode")).click();
//     driver.findElement(By.id("zipCode")).sendKeys("54321");
//     driver.findElement(By.id("creditCardNumber")).click();
//     driver.findElement(By.id("creditCardNumber")).sendKeys("111232");
//     driver.findElement(By.id("creditCardMonth")).click();
//     driver.findElement(By.cssSelector(".control-group:nth-child(9) > .controls")).click();
//     driver.findElement(By.cssSelector(".control-group:nth-child(9) > .control-label")).click();
//     driver.findElement(By.id("creditCardMonth")).sendKeys("12");
//     driver.findElement(By.cssSelector(".control-group:nth-child(9) > .controls")).click();
//     driver.findElement(By.id("nameOnCard")).click();
//     driver.findElement(By.id("nameOnCard")).sendKeys("Sussy Boy");
//     driver.findElement(By.cssSelector(".checkbox")).click();
//     assertTrue(driver.findElement(By.id("rememberMe")).isSelected());
//     {
//       String value = driver.findElement(By.id("creditCardNumber")).getAttribute("value");
//       assertThat(value, is("111232"));
//     }
//     {
//       String value = driver.findElement(By.id("nameOnCard")).getAttribute("value");
//       assertThat(value, is("Sussy Boy"));
//     }
//     driver.findElement(By.cssSelector(".btn-primary")).click();
//     assertThat(driver.getTitle(), is("BlazeDemo Confirmation"));
//   }

// }
