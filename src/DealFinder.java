import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DealFinder {

	
	
	public static void main(String[] args) 
	{
		
		boolean allowRefurbished = true;
		boolean allowOpenBox = true;
		boolean enforceStringMatch = true;
		boolean allowBundles = false;
		
		String searchTerm = args[args.length - 1].toLowerCase();
		for(int i=0; i<(args.length-1); i++)
		{
			if(args[i].equals("--allowrefurbished=f"))
			{
				allowRefurbished = false;
			}
			if(args[i].equals("--allowrefurbished=t"))
			{
				allowRefurbished = true;
			}
			if(args[i].equals("--allowopenbox=t"))
			{
				allowOpenBox = true;
			}
			if(args[i].equals("--allowopenbox=f"))
			{
				allowOpenBox = false;
			}
		}
		
		BestBuyScraper BestBuy = new BestBuyScraper(searchTerm);
		BestBuy.run();
		
		
		AmazonSiteScraper Amazon = new AmazonSiteScraper(searchTerm);
		Amazon.run();
		NeweggSiteScraper Newegg = new NeweggSiteScraper(searchTerm);
		Newegg.run();
		
//		
//		while(Newegg.done == false)
//		{
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//
//
		
		List<ItemListing> AllItems = new ArrayList<ItemListing>(); 
		AllItems.addAll(BestBuy.Items);
		AllItems.addAll(Amazon.Items);
		AllItems.addAll(Newegg.Items);
		Collections.sort(AllItems);
		int itemsFound = 0;
		for(int index = 0; index< AllItems.size() ; index++)
		{
			if(AllItems.get(index).productTitle.toLowerCase().contains(searchTerm)|| enforceStringMatch == false)
			{
				if(allowRefurbished == true || AllItems.get(index).isRefurbished == false)
				{
					if(allowOpenBox == true || AllItems.get(index).isRefurbished == false)
					{
						if(allowBundles == true || AllItems.get(index).isBundle == false)
						{

							System.out.println(AllItems.get(index).seller + ":");
							System.out.println(AllItems.get(index).productTitle);
							System.out.println("$" + AllItems.get(index).priceDollars + "." + AllItems.get(index).priceCents);
							itemsFound++;
							
						}
						
					}
				}
			}
		}
		
			
		
	}

}
