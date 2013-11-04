import java.util.ArrayList;

public class Menu 
{
    private static ArrayList<MenuItem> menuList = new ArrayList<>();
    
    //Constructor
    Menu()
    {
    
    }
    
    public void createMenuItem(String code, String name, double price)
    {    
        MenuItem food = new MenuItem(code, name, price);
        menuList.add(food);
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
