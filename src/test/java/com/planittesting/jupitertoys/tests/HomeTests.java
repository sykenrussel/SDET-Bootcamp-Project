package com.planittesting.jupitertoys.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.planittesting.jupitertoys.model.pages.HomePage;

import org.junit.jupiter.api.Test;

// import org.openqa.selenium.WebDriver; //needed for driver to switch to alert; not sure what is the proper procedure for this
// import org.openqa.selenium.Alert;

public class HomeTests extends BaseTestSuite{
    @Test
    public void verifyRedirectStar(){
        var link = new HomePage(driver)
            .clickStar()
            .getUrl();
        assertEquals("https://github.com/jeffersonRibeiro/react-shopping-cart", link);
        // tried initialising driver here as well not sure if it was better to do so
        // low risk, just wanted to experiment
    }

    @Test
    public void verifyAddItemsDifferentSize(){
        var cart = new HomePage(driver)
            .clickSizeOption("4")
            .clickAddToCart(".sc-124al1g-2.keuquD")
            .clickSizeOption("5")
            .clickAddToCart(".sc-124al1g-2.keuquD")
            .getCartLength();

        assertEquals(2, cart);
    }

    @Test
    public void verifyProductCountAfterSelectDeselect(){
        var startingCount = new HomePage(driver)
            .getItemCount();

        var productCount = new HomePage(driver)
            .clickSizeOption("2")
            .clickSizeOption("3")
            .clickSizeOption("4")
            .clickSizeOption("2")
            .clickSizeOption("3")
            .clickSizeOption("4")
            .getItemCount();

        assertEquals(startingCount, productCount);

        //i would have to implement an explicit wait until the loading element is not visible but am unsure how to do an inspect element on something that quick and is definitely a react thing
        //not using arrange-act-assert
    }

    @Test
    public void verifyCheckoutWithNoItem(){
        var result = new HomePage(driver)
            .clickCart()
            .clickCheckout()
            // .switchTo()
            // .alert()
            .getAlertMessage();

        assertEquals("Add some product in the cart!", result);
        //I get the correct message but I get unexpected alert open; i tried adding switchTo().alert() in test suite but it still doesnt work
    }

    @Test
    public void verifyCheckoutWithItems(){
        var cart = new HomePage(driver)
            .clickAddToCart(".sc-124al1g-2.dwOYCh")
            .clickAddToCart(".sc-124al1g-2.bCLaRj")
            .clickCheckout()
            //normally I should also click the cart icon but the system is programmed to automatically open cart after adding an item
            .getAlertMessage();
        
        assertEquals("Checkout - Subtotal: $ 24.15", cart);
        //same error as the one above with alert
    }

}
