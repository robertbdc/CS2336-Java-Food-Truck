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
			System.out.println("\n1.Display Menu");
         System.out.println("2.Display Server List");
         System.out.println("3.Display Table Detail");
			System.out.println("4.Restaurant Activity");
         System.out.println("5.Quit");
         System.out.println("\nEnter Choice:");

			String tmpStr = sc.nextLine();
			x = RestaurantProject.tryParseInt(tmpStr, -1);

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
                  Table curTable = r1.askForTable();
                  if (curTable != null) {
                     // Display the status of this table
               		System.out.println(curTable.toString());
                  }      
                  break;
               case 4:
					   r1.restaurantActivity();
					   break;
					case 5: 
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
