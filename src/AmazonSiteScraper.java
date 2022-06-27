import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AmazonSiteScraper {
	public WebDriver driver;
	private String searchTerm;
	public List<ItemListing> Items;
	
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
	}
	
	private void search()
	{
		this.selectCategory();
		WebElement SearchBar = this.driver.findElement(By.id("twotabsearchtextbox"));
		SearchBar.click();
		SearchBar.sendKeys(this.searchTerm);
		SearchBar.submit();
	}
	
	private void selectCategory()
	{
		Select Dropdown =new Select(  this.driver.findElement(By.id("searchDropdownBox")));
		Dropdown.selectByVisibleText("Computers");
	}
	
	private void addFilters()
	{
		WebElement FilterPane = this.driver.findElement(By.id("s-refinements"));
		
	}

}
