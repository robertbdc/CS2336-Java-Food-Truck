package restauranttester;

import java.util.ArrayList;

public class Menu
{  
    public Menu()
    {
        //Creates the Menu object and instantiate the ArrayList of MenuItems
        ArrayList<MenuItem> menuList = new ArrayList<>();
    }
    
    public void setMenuItem(String code, String name, double price)
    {
        MenuItem menuItem = new MenuItem(code, name, price);
    }
    
    @Override
    public String toString()
    {
        return " ";
    }
}
