package com.coinmarketcap.test.sample.stepDefinitions;


import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.cucumber.java.en.Given;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import com.coinmarketcap.test.sample.utilities.SingletonManager;


public class CryptoCurrencySteps {

 static String apiUrl = "";
 private static Response response;
 HashMap<String, String> currencyDetails = new LinkedHashMap<>();
 JSONObject currencyInformation ;
 

 @Given("url {string}")
 public void url(String path) {
    RestAssured.baseURI = SingletonManager.getPropertiesReader().getProperty("config.baseurl");
	 apiUrl = path;
 }

 @Then("Get the Ids of bitcoin \\(BTC), usd tether \\(USDT), and Ethereum \\(ETH)")
 public void get_the_Ids_of_bitcoin_BTC_usd_tether_USDT_and_Ethereum_ETH()  {
	 RequestSpecification request = RestAssured.given();
	
	 request.header("Content-Type", "application/json");
	 request.header("X-CMC_PRO_API_KEY", SingletonManager.getPropertiesReader().getProperty("config.apikey"));
	 
	 response = request.get(apiUrl);
	 
	 Assert.assertEquals(200, response.getStatusCode());

	 String responseBody = response.getBody().prettyPrint();
	 JSONObject json = new JSONObject(responseBody);  
	 
      JSONArray jsonArray = json.getJSONArray("data");
      for(int i = 0 ; i < jsonArray.length(); i++) {
    	  currencyDetails.put(jsonArray.getJSONObject(i).get("symbol").toString(), jsonArray.getJSONObject(i).get("id").toString());
      }
	 
	 
	 
	
 }

 @Then("convert {string} to {string}, using the {string}")
 public void convert_to_using_the(String fromCurrency, String toCurrency, String conversionUrl) {
	 RequestSpecification request = RestAssured.given();
	 request.header("Content-Type", "application/json");
	 request.header("X-CMC_PRO_API_KEY", SingletonManager.getPropertiesReader().getProperty("config.apikey"));
	 
	  String path = conversionUrl + "?amount=100&id=" + currencyDetails.get(fromCurrency).toString() + "&"+ "convert=" + toCurrency;
      response = request.get(path);
	  Assert.assertEquals(200, response.getStatusCode());

	  String jsonString = response.asString();
	  System.out.println(jsonString);
	 
 }
 
 @Given("A consumer hits {string} call for {string}")
 public void a_consumer_hits_call(String restUrl, String currencyID) {
	 RestAssured.baseURI = SingletonManager.getPropertiesReader().getProperty("config.baseurl");
	 RequestSpecification request = RestAssured.given();
		
	 request.header("Content-Type", "application/json");
	 request.header("X-CMC_PRO_API_KEY", SingletonManager.getPropertiesReader().getProperty("config.apikey"));
	 
	 response = request.get(restUrl);
	 Assert.assertEquals(200, response.getStatusCode());
	 
	 String responseBody = response.getBody().prettyPrint();
	 JSONObject json = new JSONObject(responseBody);  
	 
       currencyInformation = json.getJSONObject("data").getJSONObject(currencyID);
 }

 @Then("Confirms logo URL is {string}")
 public void confirms_logo_URL_is(String logoUrl) {
	Assert.assertTrue(currencyInformation.get("logo").toString().equalsIgnoreCase(logoUrl));
 }

 @Then("Confirms the technical_doc Url is {string}")
 public void confirms_the_technical_doc_Url_is(String technical_doc_url) {
     // Write code here that turns the phrase above into concrete actions
	 Assert.assertTrue(currencyInformation.getJSONObject("urls").getJSONArray("technical_doc").get(0).equals(technical_doc_url));
 }

 @Then("Confirms the symbol of the currency is {string}")
 public void confirms_the_symbol_of_the_currency_is(String symbol) {
     // Write code here that turns the phrase above into concrete actions
	 Assert.assertTrue(currencyInformation.get("symbol").toString().equalsIgnoreCase(symbol));
 }

 @Then("Confirms the date_added of the currency is {string}")
 public void confirms_the_date_added_of_the_currency_is(String date_added) {
     // Write code here that turns the phrase above into concrete actions
	 Assert.assertTrue(currencyInformation.get("date_added").toString().equalsIgnoreCase(date_added));
 }

 @Then("Confirms the tag associated of the currency is {string}")
 public void confirms_the_tag_associated_of_the_currency_is(String tags) {
     // Write code here that turns the phrase above into concrete actions
	 Boolean match = false;
	  for(int i=0; i<currencyInformation.getJSONArray("tags").length(); i++)
	  {
		 
		  match = currencyInformation.getJSONArray("tags").get(i).toString().equalsIgnoreCase(tags);
	 
		  if(match)
		  {
			  Assert.assertTrue(match);
			  break;
		  }
	  }
	  Assert.assertTrue(match);
 }
   
 @Given("A consumer hits {string} {string}")
 public void a_consumer_hits(String path, String id) {
	 RestAssured.baseURI = SingletonManager.getPropertiesReader().getProperty("config.baseurl");
	 RequestSpecification request = RestAssured.given();
		
	 request.header("Content-Type", "application/json");
	 request.header("X-CMC_PRO_API_KEY", SingletonManager.getPropertiesReader().getProperty("config.apikey"));
	 
	 String url = path + id;
	 response = request.get(url);
	 Assert.assertEquals(200, response.getStatusCode());
	 
	 String responseBody = response.getBody().prettyPrint();
	 JSONObject json = new JSONObject(responseBody);  
	 
       currencyInformation = json.getJSONObject("data").getJSONObject(id);
 }
}
