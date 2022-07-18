import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BestBuyItemListing extends ItemListing {
	public BestBuyItemListing(WebElement SearchResult)
	{
		
		
		System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver/");
		System.setProperty("webdriver.firefox.bin", "/home/travis/firefox/firefox");
		
		FirefoxOptions ffopt = new FirefoxOptions()
			    .addPreference("dom.webnotifications.enabled", false)
			    .addPreference("geo.enabled", false)
			    .addPreference("geo.provider.use_corelocation", false)
			    .addPreference("geo.prompt.testing", false)
			    .addPreference("geo.prompt.testing.allow", false);
		
		WebDriver driver = new FirefoxDriver(ffopt);
		
		
		this.seller = "BestBuy";
		this.productTitle = SearchResult.findElement(By.className("sku-title")).findElement(By.tagName("a")).getAttribute("innerHTML");
		this.productLink = SearchResult.findElement(By.className("sku-title")).findElement(By.tagName("a")).getAttribute("href");
		//this.priceDollars = Integer.parseInt( SearchResult.findElement(By.xpath("//div[@class='priceView-hero-price priceView-customer-price']")).findElement(By.className("sr-only")).getAttribute("innerHTML").split(">")[1].split("\\.")[0]);
		//this.priceCents= Integer.parseInt( SearchResult.findElement(By.xpath("//div[@class='priceView-hero-price priceView-customer-price']")).findElement(By.className("sr-only")).getAttribute("innerHTML").split(">")[1].split("\\.")[1]);
		
		
		this.isRefurbished = false;
		
		
		
		this.isOpenBox = false;
		
		this.isBundle = false;

		driver.get(this.productLink);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement PriceBox = driver.findElement(By.xpath("//div[contains(normalize-space(@class), 'price-box pricing-lib-price')]")).findElement(By.xpath("//div[@class='priceView-hero-price priceView-customer-price']"));
		
		this.priceDollars = Integer.parseInt( PriceBox.findElement(By.className("sr-only")).getAttribute("innerHTML").replace(",", "").split(">")[1].split("\\.")[0]);
		this.priceCents = Integer.parseInt( PriceBox.findElement(By.className("sr-only")).getAttribute("innerHTML").replace(",", "").split(">")[1].split("\\.")[1]);
		driver.close();
		
		
	}

}
