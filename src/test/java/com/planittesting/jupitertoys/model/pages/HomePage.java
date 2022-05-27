package com.planittesting.jupitertoys.model.pages; //sorry I don't know how to refactor without breaking it

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Wait Functions
    public void WaitLoad(){
        // WebDriver driver = new WebDriverWait(driver, 5).until(d -> d.findElement(By.className("sc-uhudcz-0 iZZGui")).size() != 0);
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".sc-124al1g-0.jCsgpZ")));
    }

    public void WaitAlertLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.alertIsPresent()); 
    }

    //Click Functions
    public HomePage clickStar() {
        driver.findElement(By.className("github-button")).click();
        return this;
    }

    // initial logic of getting the size button (apparently input is not existent in the element; refer to the other one using nth child selector)
    // public HomePage clickSizeOption (String option){
    //     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    //     WebElement divElement = driver.findElement(By.cssSelector(".sc-bj2vay-1.hcyKTa"));
    //     WebElement labelElement = divElement.findElement(By.tagName("label"));
    //     WebElement inputElement = labelElement.findElement(By.cssSelector("input[value="+'"'+option+'"'+"]"));
    //     WebElement spanElement = inputElement.findElement(By.tagName("span"));
    //     spanElement.click();
    //     return this;
    //     // driver.findElement(By.cssSelector("input[value="+option+"]")).click();
    //     // driver.findElement(By.xpath("span[contains(@class, 'checkmark') and text()= option]")).click();
    // }
    
    public HomePage clickSizeOption(String index){
        WaitLoad();
        WebElement spanElement = driver.findElement(By.cssSelector("div.sc-bj2vay-0.DCKcC > div:nth-child("+index+") > label > span"));
        spanElement.click();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); using implicit wait gives same results because implicit wait will find the element that is already existing = no effect
        // explicit wait doesn't work as well; my assumption is that the "Add to cart" button is still clickable even while the loading overlay exists
        return this;
    }

    public HomePage clickCart(){
        WaitLoad();
        driver.findElement(By.xpath("//button[@class='sc-1h98xa9-0 gFkyvN']")).click();
        return this;        
        // new WebDriverWait(driver, Duration.ofSeconds(5))
        //     .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".sc-1h98xa9-0.gFkyvN")));
        // failed wait and find not sure the cause
    }

    public HomePage clickAddToCart (String itemClass){
        WaitLoad();
        WebElement divElement  = driver.findElement(By.cssSelector(itemClass));
        WebElement buttonElement = divElement.findElement(By.tagName("button"));
        buttonElement.click();
        return this;
    }

    public HomePage clickCheckout(){
        WaitLoad();
        driver.findElement(By.cssSelector(".sc-1h98xa9-11.gnXVNU")).click();
        WaitAlertLoad();
        return this;
    }


    //Get Functions
    public String getUrl(){
        return driver.getCurrentUrl();
    }

    public String getItemCount(){
        WaitLoad();
        WebElement mainElement = driver.findElement(By.cssSelector(".sc-ebmerl-4.iliWeY"));
        WebElement countElement = mainElement.findElement(By.tagName("p"));
        
        String count = new String (countElement.getText());
        return count;
    }

    public String getAlertMessage(){
        WaitAlertLoad();
        String message = new String(driver.switchTo().alert().getText());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        System.out.println(message);
        return message;
    }
    
    public int getCartLength(){
        WebElement divElement = driver.findElement(By.cssSelector(".sc-7th5t8-0.jehOnP"));
        List<WebElement> len = divElement.findElements(By.cssSelector(".sc-11uohgb-0.hDmOrM"));

        System.out.println(len.size());
        return len.size();
    }
}
