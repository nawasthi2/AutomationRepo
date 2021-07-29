package com.coinmarketcap.test.sample.infrastructure.driver;

import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.coinmarketcap.test.sample.utilities.SingletonManager;

public class Setup {

    public static WebDriver driver;

    @Before
    public void setWebDriver() throws Exception {

        String browser = System.getProperty("browser");
        if (browser == null) {
            browser = "chrome";
        }
        if(System.getProperty("mode").equalsIgnoreCase("UI")){
        switch (browser) {
            case "chrome":
            	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/" + SingletonManager.getPropertiesReader().getProperty("config.chromePath"));
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            default:
                throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
        }
    }
    }
}
