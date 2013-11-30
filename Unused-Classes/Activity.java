public class Activity 
{
    private String name;
    private String server;
    private String table;
    private char   party;
    
    public Activity()
    {
        
    }
    
    public Activity(String server, String table, char party)
    {
        this.server = server;
        this.table = table;
        this.party = party;
    }
    
    public double processActivity()
    {
       return 1.0; 
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setServer(String server)
    {
        this.server = server;
    }
    
    public void setTable(String table)
    {
        this.table = table;
    }
    
    public void setParty(char party)
    {
        this.party = party;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getServer()
    {
        return server;
    }
    
    public String getTable()
    {
        return table;
    }
    
    public char getParty()
    {
        return party;
    }
    
    public String getMessage(char ch)
    {
        return "";
    }    
}
