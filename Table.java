import java.util.Scanner;
import java.util.ArrayList;

public class Table 
{
	public enum Status {
		EMPTY, SEATED, ORDERED, SERVED
	}
   
	// Keep a list of pointers to Table objects that are created
   // Used by static function findTable.
	private static ArrayList<Table> tableList = new ArrayList<>();
	
	private Status curStatus = Status.EMPTY;
	private int tableNo = -1;
	private int numGuests = 0;
   
   // A Table has an Order for each guest
   private ArrayList<Order> tableOrders = new ArrayList<>();;
	
	public Table(int num)
	{
		this.tableNo = num;
		tableList.add(this);
	}
   
	public int getTableNo() {
		return tableNo;
	}

	// Return table object if this is a good table, null if not
	public static Table findTable(int tableNo) {
		Table retVal = null;
		for(int i = 0; i < tableList.size(); i++)
		{
			if (tableList.get(i).getTableNo() == tableNo) {
				retVal = tableList.get(i);
				break;
			}
		}
		return retVal;
	}
	
	public void Arrives() {
		// Need to find out how many have arrived. If we get a number, set status
		int guests;
		Scanner sc = new Scanner(System.in);
		// Repeat until we get a valid number of guests (or cancel)
		do {
			System.out.print("Enter number of guests (blank=cancel): ");
			String numStr = sc.nextLine();
			
			if (numStr.equals("")) return;
			
			guests = RestaurantProject.tryParseInt(numStr, -1);
			if (guests < 1) {
				System.out.println("\nInvalid number of guests.");
			}
		} while (guests < 1);
		
		// Got it, let's sit them down.
		System.out.println(guests + " guests seated, waiting for them to order.");
		this.numGuests = guests;
		this.curStatus = Status.SEATED;
	}
	
	public void Orders() {
      // Order for the number of guests
      tableOrders.clear();
      for (int i = 0; i < this.numGuests; i++) {
         // Order class will take care of populating itself.
         // Just need to give it the guest position.
         tableOrders.add(new Order(i));
         if (tableOrders.get(i).cancelled()) {
            // Cancelled ordering food
            // Don't update status
            System.out.println("The order has been cancelled.");
            return;
         }
      }
      
		// Got it, let's send the order to the kitchen
		System.out.println("The order has been sent to the kitchen.");
		this.curStatus = Status.ORDERED;
	}
	
	public void Serves() {
		// Food is served
		System.out.println("Enjoy your meal!");
		this.curStatus = Status.SERVED;
	}
	
	public void Checks() {
		// Head em up and move em out!
		System.out.println("Thank you, please come again.");
		this.numGuests = 0;
		this.curStatus = Status.EMPTY;
	}
	
	public boolean canParty() {
		// Future: maybe allow parties to be added to
		return (this.curStatus == Status.EMPTY);
	}
	
	public boolean canOrder() {
		// Future: allow dessert orders after entree is delivered
		return (this.curStatus == Status.SEATED);
	}
	
	public boolean canServe() {
		return (this.curStatus == Status.ORDERED);
	}
	
	public boolean canCheck() {
		// They can also get the check (ie leave) if they haven't ordered
      return ((this.curStatus == Status.SERVED) || (this.curStatus == Status.SEATED));
	}

	public String getStatus()
	{
		String retVal = "";
		switch (this.curStatus) {
			case EMPTY:
				retVal = "Empty";
				break;
			case SEATED:
				retVal = "Seated";
				break;
			case ORDERED:
				retVal = "Ordered";
				break;
			case SERVED:
				retVal = "Served";
				break;
		}
		return retVal;
	}
   
	public String toString() {
		String retVal;
		retVal = "Table " + tableNo + " status: " + this.getStatus();
		if (this.curStatus != Status.EMPTY) {
			retVal += " (" + this.numGuests + " guests)";
		}
      retVal += "\nYour server is: ";
      retVal += Server.getServerFromTable(this).getName();
      if (this.curStatus == Status.ORDERED || this.curStatus == Status.SERVED) {
         double total = 0.00;
         retVal += "\n";
         for (int i = 0; i < this.tableOrders.size(); i++) {
            Order anOrder = this.tableOrders.get(i);
            retVal += "\n" + anOrder.toString();
            total += anOrder.getOrderTotal();
         }
         retVal += String.format("\nTable total: $%4.2f\n", total);
      }
		return retVal;
	}
	
}
