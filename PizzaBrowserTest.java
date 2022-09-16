package org.example.Lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class PizzaBrowserTest {

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
        driver.get("https://dodopizza.ru/moscow");
        driver.manage().window().fullscreen();
    }

    @AfterEach
    void killbrowser(){
        driver.quit();
    }


    @Test
    @DisplayName("Проверка, добавления одной пиццы в корзину")
    void PizzaOrder()  {
        driver.findElement(By.xpath("//img[@title='Домашняя']")).click();
        driver.findElement(By.xpath("//label[contains(.,'Большая')]")).click();
        driver.findElement(By.xpath("//label[contains(.,'Тонкое')]")).click();
        driver.findElement(By.xpath("//h2[contains(.,'Ветчина')]")).click();
        driver.findElement(By.xpath("//h2[contains(.,'Пастрами из индейки')]")).click();
        driver.findElement(By.xpath("//button[@data-size='big']")).click();
        driver.findElement(By.xpath("//button[contains(.,'Корзина')]")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(.,'К оформлению заказа')]")));
        Assertions.assertTrue(driver.findElement(By.xpath("//h3[contains(.,'Домашняя')]")).isDisplayed());
    }

    @Test
    @DisplayName("Проверка, открытия новой вкладки Акции 'Ну, всё, кешбэк' ")
    void OpenTabStock()  {
        driver.findElement(By.xpath("//a[contains(.,'Акции')]")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(.,'Ну всё, кешбэк!')]/ancestor::article/button")));
        driver.findElement(By.xpath("//h1[contains(.,'Ну всё, кешбэк!')]/ancestor::article/button")).click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Assertions.assertTrue(driver.getCurrentUrl().contains("loyaltyprogram"));
    }
















}
