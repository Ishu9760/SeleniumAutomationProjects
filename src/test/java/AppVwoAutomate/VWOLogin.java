package AppVwoAutomate;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class VWOLogin {
   ChromeOptions options;
   WebDriver driver;
   @BeforeSuite
   public void setup(){
       options = new ChromeOptions();
       options.addArguments("--start-maximized");
       driver = new ChromeDriver(options);

   }
    @Test(priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Negative Test Case")
    public void testinvalidlogin() throws InterruptedException {

        driver.get("https://app.vwo.com");
        driver.findElement(By.id("login-username")).sendKeys("93npu2yyb0@esiix.cm");
        driver.findElement(By.name("password")).sendKeys("Wingify@125553");
        driver.findElement(By.id("js-login-btn")).click();

        // explicit wait

        WebElement errormessage = driver.findElement(By.className("notification-box-description"));

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(errormessage));

        // fluent wait

//        Wait<WebDriver> wait = new FluentWait<>(driver)
//                .withTimeout(Duration.ofSeconds(10))
//                .pollingEvery(Duration.ofSeconds(2))
//                .ignoring(NoSuchElementException.class);
//
//        WebElement errormessage2 = wait.until(new Function<WebDriver, WebElement>() {
//            @Override
//            public WebElement apply(WebDriver webDriver) {
//                return driver.findElement(By.xpath("//div[@id='js-notification-box-msg']"));
//            }
//        });

        String error = driver.findElement(By.className("notification-box-description")).getText();

        Assert.assertEquals(errormessage.getText(),"Your email, password, IP address or location did not match");
        //Add Assertion in last
        //Expected == Actual
    }
    @Test(priority = 2)
    @Description("Positive Test Case")
    public void testvalidlogin() throws InterruptedException {
        driver.get("https://app.vwo.com");
        driver.findElement(By.id("login-username")).sendKeys("93npu2yyb0@esiix.com");
        driver.findElement(By.name("password")).sendKeys("Wingify@123");
        driver.findElement(By.id("js-login-btn")).click();

        WebElement errormessage = driver.findElement(By.className("notification-box-description"));

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("https://app.vwo.com/#/dashboard"));


        Assert.assertEquals(driver.getTitle(),"Dashboard");
        Assert.assertEquals(driver.getCurrentUrl(),"https://app.vwo.com/#/dashboard");
    }
    @AfterSuite
    public void teardown(){
       driver.quit();
    }


}
