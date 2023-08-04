package OrangeHrmAutomate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;

public class OrangeHR {

    ChromeOptions options;
    WebDriver driver;

    @BeforeSuite
    public void setUp() {
        options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver();
    }

    @Test
    public void validLogin() throws InterruptedException {

        driver.get("https://awesomeqa.com/hr/web/index.php/auth/login");

        Thread.sleep(2000);



        driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("Hacker@4321");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(2000);
        // Navigates to https://awesomeqa.com/hr/web/index.php/pim/viewEmployeeList
        driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")).click();

        // Navigates to https://awesomeqa.com/hr/web/index.php/pim/addEmployee

        Thread.sleep(3000);

        driver.findElement(By.name("firstName")).sendKeys("Ishu");
        driver.findElement(By.name("lastName")).sendKeys("Anand");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.getTitle(), "OrangeHRM");




    }
    @AfterSuite
    public void teardown(){
        driver.quit();
    }
}