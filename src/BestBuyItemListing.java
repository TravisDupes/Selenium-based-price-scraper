import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BestBuyItemListing extends ItemListing {
	public BestBuyItemListing(WebElement SearchResult)
	{
		this.seller = "BestBuy";
		this.productTitle = SearchResult.findElement(By.className("sku-title")).findElement(By.tagName("a")).getAttribute("innerHTML");
		this.productLink = "https://www.bestbuy.com" + SearchResult.findElement(By.className("sku-title")).findElement(By.tagName("a")).getAttribute("href");
		this.priceDollars = Integer.parseInt( SearchResult.findElement(By.xpath("//div[@class='priceView-hero-price priceView-customer-price']")).findElement(By.className("sr-only")).getAttribute("innerHTML").split(">")[1].split("\\.")[0]);
		this.priceCents= Integer.parseInt( SearchResult.findElement(By.xpath("//div[@class='priceView-hero-price priceView-customer-price']")).findElement(By.className("sr-only")).getAttribute("innerHTML").split(">")[1].split("\\.")[1]);
		
		
		this.isRefurbished = false;
		
		
		
		this.isOpenBox = false;
		
		this.isBundle = false;
	}

}
