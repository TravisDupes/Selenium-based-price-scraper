

public class ItemListing implements Comparable<ItemListing> {
	public String productTitle;
	public String productLink;
	public int rating;
	public int priceDollars;
	public int priceCents;
	public String seller;
	public boolean isRefurbished;
	public boolean isOpenBox;
	public boolean isBundle;
	
	
	
	public ItemListing()
	{
		
	}
	
	public int compareTo(ItemListing CompareItem)
	{
		return this.priceDollars - CompareItem.priceDollars;
	}
}
