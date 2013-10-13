package restauranttester;

import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Restaurant
{    
    Restaurant()
    {   
        //Creates the object of Menu
        Menu menu = new Menu();
      
        //Creates ArrayList of Server Objects
        
        //Reads file and sends the code, name and price to setMenuItem()
        String code, name;
        double price;
        
        String FILE_NAME = "C:\\Users\\Neil\\Documents\\CS2336-501\\Menu.txt";
        Path path = Paths.get(FILE_NAME);
        
        try
        {
            Scanner sc = new Scanner(path);
            
            while(sc.hasNextLine())
            {
                code = sc.next();
                name = sc.next();
                price = sc.nextDouble();               
                menu.setMenuItem(code, name, price);              
            }
        }
        catch(Exception ex)
        {
            System.out.print(ex.getCause());
        } 
        
    }
    
    public void createMenu()
    {
        //Creates the objects of MenuItems and adds those to the menu    
    }
    
    public void displayMenu()
    {

    }
    
    public void displayServerList()
    {
        //Displays the list of Servers in the 
    }
    
    public void restaurantActivity()
    {
        
    }
    
    public void processActivity()
    {
        
    }
}
