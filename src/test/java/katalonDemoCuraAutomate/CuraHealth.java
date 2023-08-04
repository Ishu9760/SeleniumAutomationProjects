package katalonDemoCuraAutomate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Collections;

public class CuraHealth {

    ChromeOptions options;
    WebDriver driver;
    @BeforeSuite
    public void setup() {
        options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(options);
    }
    @Test(priority = 1)
    public void negativecase() throws InterruptedException {

        driver.get("https://katalon-demo-cura.herokuapp.com/");

        Thread.sleep(5000);
        WebElement w = driver.findElement(By.xpath("//a[@id=\"btn-make-appointment\"]"));
        w.click();

        driver.findElement(By.xpath("//input[@id=\"txt-username\"]")).sendKeys("John Doe");
        driver.findElement(By.xpath("//input[@id=\"txt-password\"]")).sendKeys("ThisIsNotAPassword");
        driver.findElement(By.xpath("//button[@id=\"btn-login\"]")).click();
        driver.findElement(By.id("btn-make-appointment")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://katalon-demo-cura.herokuapp.com/#appointment");

        driver.findElement(By.id("combo_facility")).sendKeys("Hongkong CURA Healthcare Center");
        driver.findElement(By.id("chk_hospotal_readmission")).click();
        driver.findElement(By.xpath("//label[normalize-space()='Medicaid']")).click();
        driver.findElement(By.xpath("//input[@id='txt_visit_date']")).sendKeys("27/07/2023");
        driver.findElement(By.xpath("//textarea[@id='txt_comment']")).sendKeys("Test");
        driver.findElement(By.xpath("//button[@id='btn-book-appointment']")).click();

        Assert.assertEquals(driver.getCurrentUrl(),"https://katalon-demo-cura.herokuapp.com/appointment.php#summary");
        //Assert.assertEquals(driver.getTitle(),"Appointment Confirmation\n");
        Thread.sleep(2000);

    }

    @AfterSuite
    public void teardown(){
        driver.quit();
    }

}

