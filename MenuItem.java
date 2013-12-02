import java.util.ArrayList;

public class MenuItem
{
	// Keep a list of pointers to MenuItem objects that are created
   // Used by static function getItemFromCode.
	private static ArrayList<MenuItem> itemList = new ArrayList<>();

    private String itemCode;
    private String itemName;
    private double itemPrice;
 
    public MenuItem(String itemCode, String itemName, double itemPrice)
    {  
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        
        itemList.add(this);
    }
    
   // Given a code, return the MenuItem object that corresponds to the code
   // If code is invalid, return null
   public static MenuItem getItemFromCode(String myCode) {
      MenuItem retVal = null;
		for(int i = 0; i < itemList.size(); i++)
		{
			if (itemList.get(i).getItemCode().equals(myCode)) {
				retVal = itemList.get(i);
				break;
			}
		}
		return retVal;      
   }

    public String getItemCode()
    {
        return itemCode;
    }
    
    public String getItemName()
    {
        return itemName;
    }
    
    public double getItemPrice()
    {
        return itemPrice;
    }
    
    public String toString() {
      return String.format(" %2s %25s \t %4.2f", itemCode, itemName, itemPrice);
    }
}
