import java.util.ArrayList;

public class Server
{
    private String name;
    private Table tableList [];
    private static ArrayList<Table> tables = new ArrayList<>();
    
    public Server()
    {
        
    }
    
    public Server(String name, int [] tableArray)
    {
        this.name = name;
        tableList = new Table [tableArray.length];
        
        String tableString;
        
        for(int i = 0; i < tableArray.length; i++)
        {          
            tableString = "T" + tableArray[i];
            Table t = new Table(tableString);
            tableList[i] = t;
            tables.add(t);
        }
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getServerStatus()
    {     
        String output = "";
        for(int i = 0; i < tableList.length; i++)
        {
            output += "\t" + tableList[i].getLabel() + "(" + tableList[i].getStatus() + ")";
        }
        
        return output;
    }
    
    public String getTableLabel(String label)
    {
        String tableLabel = "Table not Found";
        
        for(int i = 0; i < tableList.length; i++)
        {
            if(label.equals(tableList[i].getLabel()))
                tableLabel = label;
        }
        
        return tableLabel;
    }
    
    public char getTableStatus(String label)
    {
        char tableStatus = '0';
        
        for(int i = 0; i < tables.size(); i++)
        {
            if(label.equals(tables.get(i).getLabel()))
                tableStatus = tables.get(i).getStatus();
        }
        
        return tableStatus;
    }
    
    public void setTabelStatus(char status, String label)
    {
        for(int i = 0; i < tables.size(); i++)
        {
            if(label.equals(tables.get(i).getLabel()))
                tables.get(i).setStatus(status);
        }
    }
}

