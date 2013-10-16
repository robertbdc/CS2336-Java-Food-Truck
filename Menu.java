package restauranttester;

import java.util.ArrayList;

public class Menu
{   
    public ArrayList<MenuItem> menuList = new ArrayList<>();
    
    public Menu()
    {
        
    }

    public void setMenuItem(String code, String name, double price)
    {      
        //menuList.add(new MenuItem(code, name, price));
    }

    @Override
    public String toString()
    {
        return " ";
    }
}
