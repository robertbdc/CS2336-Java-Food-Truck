import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu 
{
    private static ArrayList<MenuItem> menuList = new ArrayList<>();
    
    //Constructor
    Menu()
    {
    
    }
    
    public void createMenu()
    {
        //Reads file and sends the code, name and price to setMenuItem()        
        String FILE_NAME = "C:\\Users\\Neil\\Documents\\CS2336-501\\Menu.txt";
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
        System.out.println("Code \t\t Name \t\t Price");
        
        //Prints menu
        for(int i = 0; i < menuList.size(); i++)
        {
            System.out.printf("\n %2s %25s \t %4.2f", menuList.get(i).getItemCode(), menuList.get(i).getItemName(), menuList.get(i).getItemPrice());
        }
    }
}
