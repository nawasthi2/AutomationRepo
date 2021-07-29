package com.coinmarketcap.test.sample.infrastructure.driver;

import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TearDown {

    private WebDriver driver;

    public TearDown() {
        this.driver = Setup.driver;
    }

    @After
    public void quitDriver(Scenario scenario){
    	if( System.getProperty("mode").equalsIgnoreCase("UI"))
    	{
		    if(scenario.isFailed() ){
		       saveScreenshotsForScenario(scenario);
		   
		   
         }
		    this.driver.quit();
    	}
}

    @SuppressWarnings("deprecation")
	private void saveScreenshotsForScenario(final Scenario scenario) {

        final byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }
}
