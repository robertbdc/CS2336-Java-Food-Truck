import java.util.ArrayList;

public class Server
{
	// Keep a list of pointers to Server objects that are created
   // Used by static function getServerFromTable.
	private static ArrayList<Server> serverList = new ArrayList<>();

	private String name;
	private Table tableList [];
	private static ArrayList<Table> tables = new ArrayList<>();
	
	public Server(String name, ArrayList<Integer> tableArray)
	{
		this.name = name;
		tableList = new Table [tableArray.size()];
		
		String tableString;
		
		for(int i = 0; i < tableArray.size(); i++)
		{		  
			Table t = new Table(tableArray.get(i));
			tableList[i] = t;
			tables.add(t);
		}
      
      serverList.add(this);
	}
   
   public static Server getServerFromTable(Table theTable) {
		Server retVal = null;
		for(int i = 0; i < serverList.size(); i++)
		{
			if (serverList.get(i).hasTable(theTable)) {
				retVal = serverList.get(i);
				break;
			}
		}
		return retVal;
   }
   
   public boolean hasTable(Table theTable) {
      // Does this Server work this Table?
		for(int i = 0; i < tableList.length; i++)
		{
			// Future: implement Comparable and define comparator in Table class
         if (tableList[i].getTableNo() == theTable.getTableNo()) {
            // found it!
            return true;
         }
		}
      // Not found
      return false;
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
			output += "\t" + tableList[i].getTableNo() + "(" + tableList[i].getStatus() + ")";
		}
		
		return output;
	}
	
}

