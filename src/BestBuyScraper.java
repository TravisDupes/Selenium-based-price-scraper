import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BestBuyScraper {
	public WebDriver driver;
	private String searchTerm;
	public List<ItemListing> Items;
	
	public BestBuyScraper(String searchTerm)
	{
		System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver/");
		System.setProperty("webdriver.firefox.bin", "/home/travis/firefox/firefox");
		this.driver = new FirefoxDriver();
		driver.get("https://www.bestbuy.com/" );
		this.searchTerm = searchTerm;
	}
	
	public void run()
	{
		this.search();
		this.addFilters();
	}
	
	private void search()
	{
		WebElement SearchBar = this.driver.findElement(By.id("gh-search-input"));
		SearchBar.click();
		SearchBar.sendKeys(this.searchTerm);
		SearchBar.submit();
	}
	
	private void addFilters()
	{
		WebElement FilterPane = this.driver.findElement(By.id("main-filters"));
		FilterPane.findElement(By.id("soldout_facet-Exclude-Out-of-Stock-Items-0")).click();
	}
	
	private void scrape()
	{
		List<WebElement> ResultsList = this.driver.findElement(By.id("main-results")).findElements(By.className("sku-item"));
		for(WebElement listing: ResultsList)
		{
			ItemListing CurrentListing = new ItemListing();
			CurrentListing.productTitle = listing.findElement(By.className("sku-title")).findElement(By.tagName("a")).getAttribute("innerHTML");
			CurrentListing.productLink = listing.findElement(By.className("sku-title")).findElement(By.tagName("a")).getAttribute("href");
			
			
			CurrentListing.priceDollars = Integer.parseInt(listing.findElement(By.className("price")).findElement(By.tagName("strong")).getAttribute("innerHTML"));
			CurrentListing.priceCents = Integer.parseInt(listing.findElement(By.className("price")).findElement(By.tagName("sup")).getAttribute("innerHTML").substring(1));
			this.Items.add(CurrentListing);
			
			System.out.println(CurrentListing.productTitle + " $" + CurrentListing.priceDollars + "." + CurrentListing.priceCents);
		}
		
	}

}
