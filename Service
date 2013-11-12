public class Service extends Activity
{
    public Service(String label)
    {
        super.setName("Service");
        super.setTable(label);
    }
    
    @Override
    public String toString()
    {
        return "\nTable " + super.getTable() + " has now been served.";
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
        
        if(ch == 'S')
            message = "Table " + super.getTable() + " status has now been changed to: Served";
        else
            message = "Not a valid status";
        
        return message;
    }        
}
