import java.util.Scanner;

public class RestaurantProject
{
	public static void main(String[] args) 
	{
		//Restaurant initialization stuff, makes Menu - an ArrayList of MenuItem
		Restaurant r1 = new Restaurant();
	 
		//Stuff for our switch
		Scanner sc = new Scanner(System.in);
		int x;
		boolean flag = true;
		
		do
		{
			System.out.println("\n\n\tWelcome to the Java Restaurant!");
			System.out.print("\n1.Display Menu \n2.Display Server List "
				+ "\n3.Restaurant Activity \n4.Quit \n\nEnter Choice:");
	   
			x = sc.nextInt();

			try
			{
				switch(x)
				{
					case 1:
						r1.displayMenu();
						break;
					case 2:
						r1.displayServerList();
						break;
					case 3:
					r1.restaurantActivity();
					break;
					case 4: 
						flag = false;
						break;
					default:
						System.out.print("Invalid entry");
				}
			}
			catch(Exception ex) //This doesn't work yet
			{
				System.out.println("Caught message " + ex.getMessage());
			}
		}
		while(flag);
		
		System.out.println("\nThank you!");	   
	}
	
	// Helper functions

   // Parse the incoming data, return defVal if invalid
   public static double tryParseDouble(String str, double defVal) {
	  double retVal;
	  try {
		 retVal = Double.parseDouble(str);
	  }
	  catch (NumberFormatException nfe) {
		 retVal = defVal;
	  }
	  return retVal;
   }

   public static int tryParseInt(String str, int defVal) {
	  int retVal;
	  try {
		 retVal = Integer.parseInt(str);
	  }
	  catch (NumberFormatException nfe) {
		 retVal = defVal;
	  }
	  return retVal;
   }
	
}
