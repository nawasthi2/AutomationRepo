package com.coinmarketcap.test.sample;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/com/coinmarketcap/test/sample/"},
        strict = false, plugin = {"pretty",
        "json:target/cucumber_json_reports/automation-results.json",
        "html:target/automation-results-html"},
        glue = {"com.coinmarketcap.test.sample.infrastructure.driver",
                "com.coinmarketcap.test.sample.stepDefinitions"})
public class TestRunner {
}
