import java.util.ArrayList;

public class Server
{
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

