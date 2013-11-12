public class Arrival extends Activity
{
    public Arrival(String server, String table, char party)
    {
        super.setServer(server);
        super.setTable(table);
        super.setParty(party);
        super.setName("Arrival");
    }
    
    @Override
    public String toString()
    {
        return "\nWe have a " + super.getName() + "! We have a party of " + super.getParty() + " now seating at table " + super.getTable() + ". Your server will be " + super.getServer() + ".";
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
        
        if(ch == 'O')
            message = "Table " + super.getTable() + " is now Occupied";
        else
            message = "Not a valid status";
        
        return message;
    }       
}
