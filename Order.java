public class Order extends Activity
{
    private String [] itemCodeList = new String [100]; //I don't like doing this, but I couldn't think of a better way to allocate space
    private String [] itemNameList = new String [100];
    private double [] itemPriceList = new double [100];
    private int orderCounter = 0;
    
    public Order(String label)
    {
        super.setName("Order");
        super.setTable(label);
    }
    
    public void addOrder(MenuItem mi)
    {       
        itemCodeList[orderCounter] = mi.getItemCode();
        itemNameList[orderCounter] = mi.getItemName();
        itemPriceList[orderCounter] = mi.getItemPrice();
        
        orderCounter++;     
    }
    
    @Override
    public String toString()
    {
        String toString = "";
        
        System.out.println("\nWe have a " + super.getName() + "!");       
        
        for(int i = 0; i < orderCounter; i++)
        {
            toString += "Table " + super.getTable() + " just ordered " + itemNameList[i] + "\n";
        }
              
        return toString;
    }
    
    @Override
    public double processActivity()
    {
        return 1.0;
    }
    
    @Override
    public String getMessage(char ch)
    {
        String message;
        
        if(ch == 'R')
            message = "Table " + super.getTable() + " status has now been changed to: Ordered";
        else
            message = "Not a valid status";
        
        return message;
    }
}
