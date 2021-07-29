package com.coinmarketcap.test.sample.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;

public class HomePageSteps {


    private HomePage homePage;

    public HomePageSteps() {
        this.homePage = new HomePage();
    }

    @Given("A user navigates to HomePage and selects {int}")
    public void a_user_navigates_to_HomePage_and_selects(Integer showRows) {
        // Write code here that turns the phrase above into concrete actions
    	this.homePage.goToHomePage();
    }

    @Then("{int} rows are displayed")
    public void rows_are_displayed(Integer showRows) {
    	this.homePage.checkRowCountReturned(showRows);
    }
    
    @Given("A user navigates to HomePage and click on filter button and add filter by {string} and {string}")
    public void a_user_navigates_to_HomePage_and_click_on_filter_button_and_add_filter_by_and(String filter1, String filter2) {
        // Write code here that turns the phrase above into concrete actions
    	this.homePage.goToHomePage();
    	this.homePage.addFilters(filter1, filter2);
    }

    @Then("results should be filtered as per the {string} and {string} range provided.")
    public void results_should_be_filtered_as_per_the_range_provided(String filter1, String filter2) {
        // Write code here that turns the phrase above into concrete actions
        this.homePage.verifyResults(filter1, filter2);
    }

}
