package org.example.Lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumActionsTest {


    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    @BeforeAll
    static void  registerDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser(){
        driver=new ChromeDriver();
        webDriverWait= new WebDriverWait(driver, Duration.ofSeconds(5));
        actions=new Actions(driver);
        driver.manage().window().fullscreen();

    }


    @AfterEach
    void killbrowser(){
        driver.quit();
    }



    @Test
    @DisplayName("Проверка,отображения вводимого текста")
    void inputTextTest()  {
        driver.get("https://demo.seleniumeasy.com/basic-first-form-demo.html");
        WebElement textField = driver.findElement(By.id("user-message"));
        actions.sendKeys(textField,"Hello World").perform();
        driver.findElement(By.xpath("//button[@type='button'][contains(.,'Show Message')]")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//div[@id='user-message']/span[contains(.,'Hello World')]")).isDisplayed());
    }
    @Test
    @DisplayName("Проверка суммы чисел")
    void InputNumberAndCheckResult(){
        driver.get("https://demo.seleniumeasy.com/basic-first-form-demo.html");
        WebElement sum1 = driver.findElement(By.id("sum1"));
        WebElement sum2 = driver.findElement(By.id("sum2"));
        actions.sendKeys(sum1,"10")
                .sendKeys(sum2,"5")
                    .perform();
         driver.findElement(By.xpath("//button[contains(.,'Get Total')]")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//span[contains(.,'15')]")).isDisplayed());

    }

    @Test
    @DisplayName("Проверка радио кнопки")
    void CheckRadioButton() {
        driver.get("https://demo.seleniumeasy.com/basic-radiobutton-demo.html");
        driver.findElement(By.xpath("((//label[@class='radio-inline'][contains(.,'Male')])//input[@name='optradio'])")).click();
        driver.findElement(By.id("buttoncheck")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//p[contains(.,\"Male\")]")).isDisplayed());
    }

    @Test
    @DisplayName("Выбор даты")
    void Calendar(){
            driver.get("https://demo.seleniumeasy.com/bootstrap-date-picker-demo.html");
            driver.findElement(By.xpath("//div[@class='input-group date']")).click();
            driver.findElement(By.xpath("(//th[@class='prev'])[1]")).click();
            driver.findElement(By.xpath("//td[@class='day'][contains(.,'18')]")).click();
        // Подскажите пожалуйста, как можно тут сделать проверку на выбранную мною дату
            //Assertions.assertTrue(driver.findElement(By.xpath("")).isDisplayed());
    }


}
