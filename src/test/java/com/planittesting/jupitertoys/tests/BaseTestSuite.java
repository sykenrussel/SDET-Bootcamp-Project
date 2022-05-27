package com.planittesting.jupitertoys.tests;
//sorry I don't know how to refactor without breaking it

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseTestSuite {
    protected WebDriver driver;

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.navigate().to("https://react-shopping-cart-67954.firebaseapp.com/");
    }

    @AfterEach
    public void teardownTest() {
        driver.close();
    }
}
