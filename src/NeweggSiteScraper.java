import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class NeweggSiteScraper implements Runnable {
	public WebDriver driver;
	private String searchTerm;
	public List<ItemListing> Items = new ArrayList<ItemListing>();
	public boolean done = false;
	
	public NeweggSiteScraper(String searchTerm)
	{
		System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver/");
		System.setProperty("webdriver.firefox.bin", "/home/travis/firefox/firefox");
		this.driver = new FirefoxDriver();
		driver.get("https://www.newegg.com/" );
		this.searchTerm = searchTerm;
	}
	
	public void run()
	{
		this.search();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(this.driver.findElements(By.xpath("//li[.//div[.=\"In Stock\"]]")).size() > 0)
		{
			this.applyFilters();
			this.sort();
			this.scrape();
			
		}
		this.driver.close();
		this.done = true;
	}
	
	private void search()
	{
		WebElement SearchBar = this.driver.findElement(By.tagName("input"));
		SearchBar.click();
		SearchBar.sendKeys(this.searchTerm);
		SearchBar.submit();
	}
	
	private void applyFilters()
	{
		this.soldByNewegg();
		this.inStock();
	}
	
	private void soldByNewegg()
	{
		WebElement SoldByNewegg = this.driver.findElement(By.xpath("//li[.//div[.=\"Sold by Newegg\"]]"));
		if (SoldByNewegg.findElement(By.tagName("input")).getAttribute("checked") == null)
		{
			WebElement toggle = SoldByNewegg.findElement(By.tagName("label"));
			toggle.click();
		}
	}
	
	private void inStock()
	{
		WebElement InStock = this.driver.findElement(By.xpath("//li[.//div[.=\"In Stock\"]]"));
		WebElement toggle = InStock.findElement(By.tagName("label"));
		toggle.click();
	}
	
	private void sort()
	{
		WebElement SortBy = this.driver.findElement(By.className("list-tool-sortby"));
		Select Dropdown =new Select( SortBy.findElement(By.tagName("select")));
		Dropdown.selectByVisibleText("Lowest Price");
	}
	
	private void scrape()
	{
		List<WebElement> SearchResults = this.driver.findElement(By.className("list-wrap")).findElements(By.className("item-container"));
		for(WebElement listing: SearchResults)
		{
			this.Items.add(new NeweggItemListing(listing));
			
		}
	}
	


}
