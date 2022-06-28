
public class DealFinder {
	public static void main(String[] args) 
	{
		NeweggSiteScraper Newegg = new NeweggSiteScraper("videocard");
		Newegg.run();
		while(Newegg.done == false)
		{
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(ItemListing item: Newegg.Items)
		{

			System.out.println(item.seller + ":");
			System.out.println(item.productTitle);
			System.out.println("$" + item.priceDollars + "." +item.priceCents);
		}
	}

}
