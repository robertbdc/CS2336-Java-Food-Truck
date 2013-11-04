
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Restaurant 
{
    private static ArrayList<Server> servers = new ArrayList<>();
    
    Restaurant()
    {
        Menu m1 = new Menu();
        
        //Reads file and sends the code, name and price to setMenuItem()        
        String FILE_NAME = "D:\\My Documents\\Menu.txt";
        Path path = Paths.get(FILE_NAME);
        
        String code, name;
        double price;
        
        try
        {
            Scanner sc = new Scanner(path);
            
            while(sc.hasNextLine())
            {
                code = sc.next();
                name = sc.next();
                price = sc.nextDouble();               
                m1.createMenuItem(code, name, price);
            }
        }
        catch(Exception ex)
        {
            System.out.print(ex.getCause());
        } 
        
        //Read in server file 
        FILE_NAME = "D:\\My Documents\\Servers.txt";
        path = Paths.get(FILE_NAME);
        
        String serverName, temp;
        int [] tables = new int [6];
        
        try
        {
            Scanner sc = new Scanner(path).useDelimiter(" ");
            
            while(sc.hasNextLine())
            {
                //Get the name from the file, then stop

                temp = sc.next();
                serverName = temp;
                
                //Read in and format our lines
                temp = sc.nextLine().replace(",", " ");
                String [] parsed = temp.split(" ");
                
                //take formatted lines and make them into arrays
                tables[0] = Integer.parseInt(parsed[1]);
                tables[1] = Integer.parseInt(parsed[2]);
                tables[2] = Integer.parseInt(parsed[3]);
                tables[3] = Integer.parseInt(parsed[4]);
                tables[4] = Integer.parseInt(parsed[5]);
                tables[5] = Integer.parseInt(parsed[6]);
                
                Server s1 = new Server(serverName, tables);
                servers.add(s1);
            }
        }
        catch(IOException | NumberFormatException ex)
        {
            System.out.print(ex.getCause());
        }
    } 
    
    public void displayMenu()
    {
        Menu m2 =  new Menu();
        m2.displayMenu();
    }
    
    public void displayServerList()
    {
        for(int i = 0; i < servers.size(); i++)
        {
            System.out.println(servers.get(i).getName());    
        }
    }
    
    public void restaurantActivity()
    {
        
    }
    
    public void processActivity()
    {
        
    }  
}
