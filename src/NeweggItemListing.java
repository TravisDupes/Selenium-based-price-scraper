import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NeweggItemListing extends ItemListing {

	public NeweggItemListing(WebElement Listing) {
		this.productTitle = Listing.findElement(By.className("item-title")).getAttribute("innerHTML");
		this.productLink = Listing.findElement(By.className("item-title")).getAttribute("href");
		this.priceDollars = Integer.parseInt(Listing.findElement(By.className("price")).findElement(By.tagName("strong")).getAttribute("innerHTML"));
		this.priceCents = Integer.parseInt(Listing.findElement(By.className("price")).findElement(By.tagName("sup")).getAttribute("innerHTML").substring(1));
		this.seller = "Newegg";
		if (this.productTitle.contains("Refurbished"))
		{
			this.isRefurbished = true;
		}
		else
		{
			this.isRefurbished = false;
		}
		
		if (this.productTitle.contains("Open Box"))
		{
			this.isOpenBox = true;
		}
		else
		{
			this.isOpenBox = false;
		}
		
	}

}
