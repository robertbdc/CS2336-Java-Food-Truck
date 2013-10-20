public class MenuItem
{
    private String itemCode;
    private String itemName;
    private double itemPrice;
 
    public MenuItem(String itemCode, String itemName, double itemPrice)
    {  
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
    
    public MenuItem()
    {
        
    }
    
    public void setItemCode(String itemCode)
    {
        this.itemCode = itemCode;
    }
    
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }
    
    public void setItemPrice(double itemPrice)
    {
         this.itemPrice = itemPrice;
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
}
