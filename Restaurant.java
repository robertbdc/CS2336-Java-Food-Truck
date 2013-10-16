package restauranttester;

import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Restaurant extends Menu
{    
    Restaurant()
    {
         //Creates ArrayList of Server Objects      
    }
    
    public void createMenu()
    {
        //Creates the object of Menu
        Menu menu = new Menu();
        
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
                MenuItem food = new MenuItem(code, name, price);
                menuList.add(food);
            }
        }
        catch(Exception ex)
        {
            System.out.print(ex.getCause());
        }       
    }
    
    public void displayMenu()
    {
        //fix this output
        for(int i = 0; i < menuList.size(); i++)
        {
            System.out.print(menuList.get(i).getItemName());    
        }
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
