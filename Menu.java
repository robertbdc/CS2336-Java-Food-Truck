import java.util.ArrayList;

public class Menu 
{
    private ArrayList<MenuItem> menuList = new ArrayList<>();
    
    //Constructor
    Menu()
    {
    
    }
    
    public void createMenuItem(String code, String name, double price)
    {    
        MenuItem food = new MenuItem(code, name, price);
        menuList.add(food);
    }
       
    public String toString()
    {
        String outString = "Code \t\t Name \t\t Price\n";
        
        //Prints menu
        for(int i = 0; i < menuList.size(); i++)
        {
            outString += menuList.get(i).toString();
            outString += "\n";
        }
        return outString;
    }
    
}
