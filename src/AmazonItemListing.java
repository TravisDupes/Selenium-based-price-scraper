import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonItemListing extends ItemListing {

	public AmazonItemListing(WebElement SearchResult) 
	{
		this.seller = "Amazon";
		this.productTitle = SearchResult.findElement(By.tagName("h2")).findElement(By.tagName("span")).getAttribute("innerHTML");
		this.productLink = "https://www.amazon.com" + SearchResult.findElement(By.tagName("h2")).findElement(By.tagName("a")).getAttribute("href");
		this.priceDollars = Integer.parseInt(SearchResult.findElement(By.className("a-price-whole")).getAttribute("innerHTML").split("<")[0]);
		this.priceCents= Integer.parseInt(SearchResult.findElement(By.className("a-price-fraction")).getAttribute("innerHTML"));
		
		if (this.productTitle.contains("Renewed"))
		{
			this.isRefurbished = true;
		}
		else
		{
			this.isRefurbished = false;
		}
		
		
		this.isOpenBox = false;
		
		this.isBundle = false;

		
	}

}
