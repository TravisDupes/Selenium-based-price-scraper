import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BestBuyScraper {
	public WebDriver driver;
	private String searchTerm;
	public List<ItemListing> Items  = new ArrayList<ItemListing>();
	public boolean done = false;
	
	public BestBuyScraper(String searchTerm)
	{
		System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver/");
		System.setProperty("webdriver.firefox.bin", "/home/travis/firefox/firefox");
		
		FirefoxOptions ffopt = new FirefoxOptions()
			    .addPreference("dom.webnotifications.enabled", false)
			    .addPreference("geo.enabled", false)
			    .addPreference("geo.provider.use_corelocation", false)
			    .addPreference("geo.prompt.testing", false)
			    .addPreference("geo.prompt.testing.allow", false);
		
		this.driver = new FirefoxDriver(ffopt);
		driver.get("https://www.bestbuy.com/" );
		this.searchTerm = searchTerm;
	}
	
	public void run()
	{
		this.search();
		this.addFilters();
		this.scrape();
		this.driver.close();
		this.done = true;
	}
	
	private void search()
	{
		WebElement SearchBar = this.driver.findElement(By.id("gh-search-input"));
		SearchBar.click();
		SearchBar.sendKeys(this.searchTerm);
		SearchBar.submit();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addFilters()
	{
		WebElement FilterPane = this.driver.findElement(By.id("main-filters"));
		FilterPane.findElement(By.linkText("Exclude Out of Stock Items")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void scrape()
	{
		List<WebElement> ResultsList = this.driver.findElement(By.className("sku-item-list")).findElements(By.className("sku-item"));
		for(WebElement listing: ResultsList)
		{
			this.Items.add(new BestBuyItemListing(listing));
			
		}
		
	}

}
