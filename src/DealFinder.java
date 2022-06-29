import java.util.Collections;

public class DealFinder {

	
	
	public static void main(String[] args) 
	{
		
		boolean allowRefurbished = true;
		boolean allowOpenBox = true;
		boolean enforceStringMatch = false;
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
		
		
		NeweggSiteScraper Newegg = new NeweggSiteScraper(searchTerm);
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
		


		for(ItemListing item: Newegg.Items.subList(0, 5))
		{
			if(item.productTitle.toLowerCase().contains(searchTerm)|| enforceStringMatch == false)
			{
				if(allowRefurbished == true || item.isRefurbished == false)
				{
					if(allowOpenBox == true || item.isRefurbished == false)
					{
						if(allowBundles == true || item.isBundle == false)
						{

							System.out.println(item.seller + ":");
							System.out.println(item.productTitle);
							System.out.println("$" + item.priceDollars + "." +item.priceCents);
						}
						
					}
				}
			}

			
		}
	}

}
