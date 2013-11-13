import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.*;

public class Restaurant
{
    private static ArrayList<Server> servers = new ArrayList<>();
    
    Restaurant()
    {
        Menu m1 = new Menu();
        
        //Reads file and sends the code, name and price to setMenuItem()        
        String FILE_NAME = "D:\\My Documents\\Menu.txt";
        if (!(new File(FILE_NAME).isFile())) {
            // Default doesn't exist, but we need to get a file from somewhere!
            PickFile thisFile = new PickFile();
            FILE_NAME = thisFile.getFullFileName();
            if (FILE_NAME.equals("")) {
               System.out.println("Menu file was not selected.");
               System.exit(0); // curl up and die
            }
         }   

        Path path = Paths.get(FILE_NAME);
        
        String code, name;
        double price;
        
        try
        {
            Scanner sc = new Scanner(path);
            
            while(sc.hasNextLine())
            {
                code = sc.next();
                name = sc.next().replace("_", " ");
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
        if (!(new File(FILE_NAME).isFile())) {
            // Default doesn't exist, but we need to get a file from somewhere!
            PickFile thisFile = new PickFile();
            FILE_NAME = thisFile.getFullFileName();
            if (FILE_NAME.equals("")) {
               System.out.println("Servers file was not selected.");
               System.exit(0); // curl up and die
            }
         }   
        path = Paths.get(FILE_NAME);
        
        String serverName, temp;
        int [] tables;
        
        try
        {
            Scanner sc = new Scanner(path).useDelimiter(" ");
            
            while(sc.hasNextLine())
            {
                //Get the name from the file, then stop

                temp = sc.next();
                serverName = temp;
                
                //Read in and format our lines
                temp = sc.nextLine().replace(" ", "");
                temp = temp.replace((","), " ");
                String [] parsed = temp.split(" ");
      
                tables = new int [parsed.length];
                
                //take formatted lines and make them into arrays
                for(int i = 0; i < parsed.length; i++)
                {                    
                    tables[i] = Integer.parseInt(parsed[i]);
                }               
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
        System.out.println("\nName\tTables");
        for(int i = 0; i < servers.size(); i++)
        {
            System.out.println(servers.get(i).getName() + servers.get(i).getServerStatus());         
        }
    }
    
    public void restaurantActivity()
    {
        String activity;
        Scanner sc = new Scanner(System.in);
        
        System.out.print("\nEnter activity:");     
        activity = sc.nextLine();

        processActivity(activity);
    }
    
    public void processActivity(String activity)
    {       
        String [] element = activity.split("\\s");
        
        for(int i = 0; i < servers.size(); i++)
        {
            try
            {              
            //if the activity is always "T##....." this checks to make sure the table is valid
            if(element[0].equals(servers.get(i).getTableLabel(element[0])))
            {              
                //if the activity is always "T## P##" when seating guests, this checks for a P and creates the arrival stuff
                if(element[1].charAt(0) == 'P')
                {
                    Arrival arrival = new Arrival(servers.get(i).getName(), servers.get(i).getTableLabel(element[0]), element[1].charAt(1));
                    
                    System.out.println(arrival.toString());
                    servers.get(i).setTabelStatus('O', element[0]);
                    System.out.println(arrival.getMessage('O'));
                }
                else if(element[1].charAt(0) == 'O')
                {
                    Menu m3 = new Menu();
                    Order order = new Order(servers.get(i).getTableLabel(element[0]));
                    
                    for(int j = 2; j < element.length; j++)
                    {
                        order.addOrder(m3.getMenuItem(element[j]));
                    }
                    
                    System.out.println(order.toString());
                    servers.get(i).setTabelStatus('R', element[0]);
                    System.out.println(order.getMessage('R'));
                }
                else if(element[1].charAt(0) == 'S')
                {
                    Service service = new Service(servers.get(i).getTableLabel(element[0]));
                    
                    System.out.println(service.toString());
                    servers.get(i).setTabelStatus('S', element[0]);
                    System.out.println(service.getMessage('S'));
                }
                else if(element[1].charAt(0) == 'C')
                {
                    Menu m4 = new Menu();
                    Check check = new Check(servers.get(i).getTableLabel(element[0]));
                    
                    for(int k = 2; k < element.length; k++)
                    {
                        check.setTotalCheck(m4.getMenuItem(element[k]));
                    }
                    
                    System.out.println(check.toString());
                    servers.get(i).setTabelStatus('A', element[0]);
                    System.out.println(check.getMessage('A'));
                }
                else
                {
                    System.out.print("\nYou did not enter a valid service code.  "
                            + "Please try again using the format T## <service code> ... "
                            + "\nService codes are: P (seat), O (order), S (serve), and C (check).");
                }
            }
            }
            catch(RuntimeException ex) //This isn't working yet
            {
                System.out.println(ex.toString());
                
            }
        }
    }  
}

