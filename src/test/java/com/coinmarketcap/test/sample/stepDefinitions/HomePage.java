package com.coinmarketcap.test.sample.stepDefinitions;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.coinmarketcap.test.sample.basepage.BasePage;
import com.coinmarketcap.test.sample.utilities.SingletonManager;

public class HomePage extends BasePage{

    private static final String HOME_PAGE_URL = SingletonManager.getPropertiesReader().getProperty("config.url");

    
    @FindBy(xpath = "//div[@class='sc-16r8icm-0 tu1guj-0 cSTqvK']")
    private WebElement selectButton;
    
    @FindBy(xpath = "//div[@class='tippy-content']//button[@class='x0o17e-0 bnhhNH']")
    private WebElement selectrRowCountFromDropDown;
    
    
    @FindAll({@FindBy(xpath = "//div[@class='h7vnx2-1 bFzXgL']//table//tbody/tr")})
    private List<WebElement> rowCountRetunred;
    
    @FindBy(xpath = "//div[@display='flex']//following-sibling::div//button[@class='x0o17e-0 ewuQaX sc-36mytl-0 bBafzO table-control-filter']")
    private WebElement filterButton;
    
    @FindBy(xpath = "//ul[@class='sc-1erqz0q-0 kSFTVn']//li[not(contains(@class,'filter'))]")
    private WebElement addFilter;
    
    @FindBy(xpath = "//button[@class='x0o17e-0 iOedBp cmc-filter-button' and text()='Market Cap']")
    private WebElement marketCapFilter;

    @FindBy(xpath = "//button[@class='x0o17e-0 iOedBp cmc-filter-button' and text()='Price']")
    private WebElement priceFilter;
    
    @FindBy(xpath = "//input[@data-qa-id='range-filter-input-min']")
    private WebElement inputMin;
    
    @FindBy(xpath = "//input[@data-qa-id='range-filter-input-max']")
    private WebElement inputMax;

    @FindBy(xpath = "//button[@data-qa-id='filter-dd-button-apply']")
    private WebElement applyFilter;
    
    @FindBy(xpath = "//button[@class='x0o17e-0 lgnbfA cmc-filter-button']")
    private WebElement showResults;
    
    @FindAll({@FindBy(xpath = "//table[@class='h7vnx2-2 bFpGkc cmc-table  ']//tbody//tr")})
    private List<WebElement> filterRowsRetunred;
    
    @FindBy(xpath = "//table[@class='h7vnx2-2 bFpGkc cmc-table  ']")
    private WebElement stockTable;
    
    HomePage() {
        PageFactory.initElements(driver, this);
    }

    void goToHomePage(){
    	driver.manage().window().maximize();
        driver.get(HOME_PAGE_URL);
        wait.forLoading(5);
    }

    
    void checkRowCountReturned(Integer rowCount)
    {
    	
    	selectButton.click();
    	wait.forLoading(5);
        selectrRowCountFromDropDown.click();
    	
    	wait.forLoading(5);
    	List<WebElement> totalRowCount = rowCountRetunred;
    	
    	System.out.println("expected row count is "+ totalRowCount.size() + " and actual row count is " + rowCount  );
    	
    	Assert.assertTrue(totalRowCount.size()==rowCount);
    }
    
    void clickOnFilterButtonAndAddFilter()
    {
    	filterButton.click();
    	wait.forElementToBeDisplayed(100, addFilter, "Add Filter" );
    	addFilter.click();
    }
    
    void addFilters(String filter1, String filter2)
    {
    	clickOnFilterButtonAndAddFilter();
    	
    	marketCapFilter.click();
    	inputMin.sendKeys(filter1.split(":")[0]);
    	inputMax.sendKeys(filter1.split(":")[1]);
    	applyFilter.click();
    	
    	priceFilter.click();
    	inputMin.sendKeys(filter2.split(":")[0]);
    	inputMax.sendKeys(filter2.split(":")[1]);   
    	applyFilter.click();
    	
    	showResults.click();
    	
    }
    
    void verifyResults(String filter1, String filter2)
    {
    	wait.forElementToBeDisplayed(1000, stockTable, "stockTable" );
    	
    	List<WebElement> totalRows = filterRowsRetunred;
    	
    	for(int i=1; i<=totalRows.size(); i++)
    	{ 		
    		
    		String pricesReturned = "//table[@class='h7vnx2-2 bFpGkc cmc-table  ']//tbody//tr[" + i +"]//td//div[@class='sc-131di3y-0 cLgOOr']"; 
    		String marketCapsReturned = "//table[@class='h7vnx2-2 bFpGkc cmc-table  ']//tbody//tr[" + i +"]//td//span[@class='sc-1ow4cwt-1 ieFnWP']"; 
    		
    		String priceValue = driver.findElement(By.xpath(pricesReturned)).getText().replace("$", "").replaceAll(",", "");
    		String marketCapsValue = driver.findElement(By.xpath(marketCapsReturned)).getText().replace("$", "").replaceAll(",", "");
    		
    		if(Double.valueOf(priceValue) < Double.valueOf(filter2.split(":")[0]) || 
    				Double.valueOf(priceValue) > Double.valueOf(filter2.split(":")[1]) ||
    				Double.valueOf(marketCapsValue) < Double.valueOf(filter1.split(":")[0]) ||
    				Double.valueOf(marketCapsValue) > Double.valueOf(filter1.split(":")[1]) )
    		{
    			System.out.println("Double.valueOf(priceValue) is ------> " + priceValue);
    			System.out.println("filter1.split(\":\")[0] ------> " + filter2.split(":")[0] + "    " + filter2.split(":")[1]);
    			System.out.println("Double.valueOf(marketCapsValue) is ------> " + marketCapsValue);
    			System.out.println("filter2.split(\":\")[0] ------> " + filter1.split(":")[0] + "    " + filter1.split(":")[1]);
    			
    			Assert.assertTrue(false);
    			break;
    		}
    		
    	}
    	
    	Assert.assertTrue(true);
    	
    }
}
