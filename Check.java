public class Check extends Activity
{
    private double totalCheck;
    
    public Check(String label)
    {
        super.setName("Check");
        super.setTable(label);
    }
    
    public void setTotalCheck(MenuItem mi)
    {
        totalCheck += mi.getItemPrice();
    }
    
    @Override
    public String toString()
    {
        return "\nTable " + super.getTable() + " is checking out with a total of $" + totalCheck + ".";
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
        
        if(ch == 'A')
            message = "Thank you for eating with us! \nTable " + super.getTable() + " status has now been changed to: Available";
        else
            message = "Not a valid status";
        
        return message;
    }
}
