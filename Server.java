import java.util.ArrayList;

public class Server 
{
    private String name;
    private static ArrayList<Table> tables = new ArrayList<>();
    
    public Server(String name, int [] table)
    {
        this.name = name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getStatus()
    {
        return " ";
    }

}
