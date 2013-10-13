package restauranttester;

import java.util.Scanner;

public class RestaurantTester
{
    public static void main(String[] args) 
    {
        Restaurant r1 = new Restaurant();
        Scanner sc = new Scanner(System.in);
        
        int x;
        boolean flag = true;
        
        do
        {
            System.out.println("\n\n\tWelcome to the Java Restaurant!");
            System.out.print("\n1.Display Menu \n2.Display Server List "
                + "\n3.Restaurant Activity \n4.Quit \n\nEnter Choice:");
       
            x = sc.nextInt();

            switch(x)
            {
                case 1:
                    break;
                case 2:
                
                    break;
                case 3:
                   
                    break;
                case 4: 
                    flag = false;
                    break;
                default:
                    System.out.print("Invalid entry");
            }
            

        }
        while(flag);
        
        System.out.println("\nThank you!");
       
    }    
}
