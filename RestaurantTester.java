import java.util.Scanner;

public class RestaurantProject //you might need to change this class name
{
    public static void main(String[] args) 
    {
        //Restaurant initialization stuff, makes Menu - an ArrayList of MenuItem
        Restaurant r1 = new Restaurant();
     
        //Stuff for our switch
        Scanner sc = new Scanner(System.in);
        int x;
        boolean flag = true;
        
        do
        {
            System.out.println("\n\n\tWelcome to the Java Restaurant!");
            System.out.print("\n1.Display Menu \n2.Display Server List "
                + "\n3.Restaurant Activity \n4.Quit \n\nEnter Choice:");
       
            x = sc.nextInt();

            try
            {
            switch(x)
            {
                case 1:
                    r1.displayMenu();
                    break;
                case 2:
                    r1.displayServerList();
                    break;
                case 3:
                    do
                    {
                        System.out.print("\n\n1.Restaurant activity\n2.Quit"
                                + "\n\nEnter Choice :");
                        x = sc.nextInt();
                        
                        switch(x)
                        {
                            case 1:
                                sc.nextLine();
                                r1.restaurantActivity();
                                break;
                            case 2:
                                break;
                            default:
                                System.out.print("Invalid Entry");
                        }
                    }
                    while(x != 2);
                    break;
                case 4: 
                    flag = false;
                    break;
                default:
                    System.out.print("Invalid entry");
            }
            }
            catch(Exception ex) //This doesn't work yet
            {
                System.out.println("Caught message " + ex.getMessage());
            }
        }
        while(flag);
        
        System.out.println("\nThank you!");       
    }
}
