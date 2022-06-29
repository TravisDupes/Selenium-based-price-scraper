import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AmazonSiteScraper {
	public WebDriver driver;
	private String searchTerm;
	public List<ItemListing> Items  = new ArrayList<ItemListing>();
	public boolean done = false;
	
	public AmazonSiteScraper(String searchTerm)
	{
		System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver/");
		System.setProperty("webdriver.firefox.bin", "/home/travis/firefox/firefox");
		this.driver = new FirefoxDriver();
		driver.get("https://www.amazon.com/" );
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
		this.selectCategory();
		WebElement SearchBar = this.driver.findElement(By.id("twotabsearchtextbox"));
		SearchBar.click();
		SearchBar.sendKeys(this.searchTerm);
		SearchBar.submit();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void selectCategory()
	{
		this.driver.findElement(By.id("nav-search-dropdown-card")).click();
		this.driver.findElement(By.id("searchDropdownBox")).sendKeys("c");
		this.driver.findElement(By.id("searchDropdownBox")).sendKeys("c");
		this.driver.findElement(By.id("searchDropdownBox")).sendKeys("c");
		this.driver.findElement(By.id("searchDropdownBox")).sendKeys("c");
		this.driver.findElement(By.id("searchDropdownBox")).sendKeys("c");
		this.driver.findElement(By.id("searchDropdownBox")).submit();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addFilters()
	{
		WebElement FilterPane = this.driver.findElement(By.id("s-refinements"));
		WebElement SellerPane = FilterPane.findElement(By.xpath("//ul[@aria-labelledby='p_6-title']"));
		SellerPane.findElement(By.xpath("//span[.='Amazon.com']")).click();
	}
	
	private void scrape()
	{
		List<WebElement> ResultsList = this.driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));
		for(WebElement listing :ResultsList)
		{
			this.Items.add(new AmazonItemListing(listing));
		}
	}

}
