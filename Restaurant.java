import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.*;
import java.sql.*;

public class Restaurant
{
    private static ArrayList<Server> servers = new ArrayList<>();
    
    Restaurant()
    {
        Menu m1 = new Menu();
        
        // Replace file-parsing with database retrieval
        // (though a redesign would probably have db activity happen in individual modules)        
        try {
          Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
          throw new RuntimeException("Can't load database driver. Must add to the classpath.", e);
        }

        String url = "jdbc:mysql://localhost:3306/restaurant";
        String username = "root";
        String password = "";
        Connection connection = null;
        try {
          connection = DriverManager.getConnection(url, username, password);
          System.out.println("Database connected!");
          
          // Load the menu file
          Statement stmt = connection.createStatement();
          String sql = "SELECT Category, ItemNo, ItemName, Price from menu";
          ResultSet rs = stmt.executeQuery(sql);
         
          // ResultSet is initially before the first data set
          while (rs.next()) {
            // Future: separate Category and ItemNo.
            String code = rs.getString("Category") + rs.getString("ItemNo");
            String name = rs.getString("ItemName");
            Double price = rs.getDouble("Price");
            
            m1.createMenuItem(code, name, price);
          }   
         
          System.out.println("Close resultset and statement");
          rs.close();
          stmt.close();
          
          // Load the server file
          // Have to pass Name and Tables[] to Server constructor
          // For each Name, get Tables. Then construct Server.
          stmt = connection.createStatement();
          sql = "Select WaiterName, WaiterSeqNo from waiter";
          rs = stmt.executeQuery(sql);

          // Java doc: "only one ResultSet object per Statement object can be open at the same time"
          // So create an object for our table lists
          Statement stmtTable;
          ResultSet rsTable;
         
          // ResultSet is initially before the first data set
          while (rs.next()) {
            String serverName = rs.getString("WaiterName");
            int serverNo = rs.getInt("WaiterSeqNo");
            
            // Have server, get array of tables
            // Note, we want the Table No (physical table), not the Table SeqNo
            sql = String.format("Select TableNo from tablelist where WaiterSeqNo = %d", serverNo);
            stmtTable = connection.createStatement();
            rsTable = stmtTable.executeQuery(sql);
            
            // Standard recordset doesn't give us a record count,
            // so we'll have to use an arrayList
            ArrayList<Integer> tables = new ArrayList<>();
            
            while (rsTable.next()) {
               tables.add(rsTable.getInt("TableNo"));
            }
            rsTable.close();
            stmtTable.close();
            
            // Have server and table list
            Server s1 = new Server(serverName, tables);
            servers.add(s1);
          }   
         
          System.out.println("Close resultset and statement");
          rs.close();
          stmt.close();
        } catch (SQLException e) {
          throw new RuntimeException("Can't read from database.", e);
        } finally {
          // Always want to close the connection!
          if (connection != null) {
            try {
               connection.close();
            } catch (SQLException ignore) {}
          }   
        }
        
        
    } // End Restaurant constructor 
    
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
    }  // End ProcessActivity

} // End class Restaurant