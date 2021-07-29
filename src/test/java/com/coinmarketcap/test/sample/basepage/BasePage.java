package com.coinmarketcap.test.sample.basepage;

import org.openqa.selenium.WebDriver;

import com.coinmarketcap.test.sample.infrastructure.driver.Setup;
import com.coinmarketcap.test.sample.infrastructure.driver.Wait;

public class BasePage {

    protected WebDriver driver;
    protected Wait wait;

    public BasePage() {
        this.driver = Setup.driver;
        this.wait = new Wait(this.driver);
    }
}
