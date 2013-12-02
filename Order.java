import java.util.Scanner;
import java.util.ArrayList;

public class Order
{
    private int mySeat;
    private boolean didCancel = false; // set if user cancels, read from .cancelled
    private ArrayList<MenuItem> orderedItems = new ArrayList<>();
    
    public Order(int seat)
    {
        boolean done = false;
        Scanner sc = new Scanner(System.in);
        String tmpStr;

        this.orderedItems.clear();
        this.mySeat = seat;
        
        System.out.println("Enter one or more menu items, separated by spaces.");
        System.out.println("Example: A1 E3 D5 B2");
        System.out.println("Or enter M for Menu, or blank when done (or guest orders nothing)");
        System.out.println("Enter C to cancel the *entire* table's order.");
        
        do {
         System.out.print("Guest " + (this.mySeat + 1) + ": item(s), M, C, blank=done: ");
			tmpStr = sc.nextLine();
         tmpStr = tmpStr.toUpperCase();
			
			if (!tmpStr.equals("")) {
            if (tmpStr.equals("M")) {
               System.out.println(Restaurant.getMenu().toString());
            } else {
               if (tmpStr.equals("C")) {
                  System.out.println("Table cancelled!");
                  didCancel = true;
                  return;
               }
               
               // Should be a string of menu items
               String errorMsg = "";
               String[] items = tmpStr.split("\\s"); // split on whitespace
               for (int i = 0; i < items.length; i++)
               {
                  MenuItem curItem = MenuItem.getItemFromCode(items[i]);
                  if (curItem == null)
                  {
                     errorMsg += " / " + items[i];
                  } else {
                     orderedItems.add(curItem);
                  }
               }
               
               // Display current order
               System.out.println(this.toString());
               // Also show errors in this round
               if (!errorMsg.equals(""))
               {
                  System.out.println("Unknown codes" + errorMsg);
               }
            } // if not M
         } // if not blank
        
        } while (!tmpStr.equals(""));
        
    }
    
    public boolean cancelled() {
      return didCancel;
    }
    
    public String toString()
    {
        String outString;
        
        if (orderedItems.size() == 0)
        {
           outString = "No current order for guest " + (this.mySeat + 1) + ":\n";
        } else {
           outString = "Current order for guest " + (this.mySeat + 1) + ":\n";
           for (int i = 0; i < orderedItems.size(); i++)
           {
            outString += orderedItems.get(i).toString();
            outString += "\n";
           }
        }
        return outString;
    }
    
}
