public class Table 
{
    private String label;
    private char   status = 'A';
    
    public Table()
    {
        
    }
    
    public Table(String label)
    {
        this.label = label;
    }
    
    public void setLabel(String label)
    {
        this.label = label;
    }
    
    public void setStatus(char status)
    {
        this.status = status;
    }
    
    public String getLabel()
    {
        return this.label;
    }
    
    public char getStatus()
    {
        return this.status;
    }
}
