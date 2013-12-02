import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.*;
import java.sql.*;

public class Restaurant
{
	private static ArrayList<Server> servers = new ArrayList<>();
   private static Menu theMenu;
	
	public Restaurant()
	{
		theMenu = new Menu();
		
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
			System.out.println("Connecting to database, please wait...");
			connection = DriverManager.getConnection(url, username, password);
			
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
				
				theMenu.createMenuItem(code, name, price);
			}   
		 
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
	
   public static Menu getMenu()
   {
      return theMenu;
   }
   
	public void displayMenu()
	{
      System.out.println(theMenu.toString());
	}
	
	public void displayServerList()
	{		 
		System.out.println("\nServer status:");
		for(int i = 0; i < servers.size(); i++)
		{
			System.out.println(servers.get(i).getName());
		 System.out.println(servers.get(i).getServerStatus());		 
		}
	}
   
   public Table askForTable()
   {
		Scanner sc = new Scanner(System.in);
      int tableNo;
      Table curTable;

		// Repeat until we get a valid table number (or cancel)
		do {
			System.out.print("Enter table number (blank=cancel): ");
			String tableStr = sc.nextLine();
			
			if (tableStr.equals("")) return null;
			
			tableNo = RestaurantProject.tryParseInt(tableStr, -1);
			
			// See if this is a good table
			curTable = Table.findTable(tableNo);
			if (curTable == null) {
				System.out.println("\nInvalid table number.");
			}
		} while (curTable == null);
		
      return curTable;
   }
	
	// Note: this combines original restaurantActivity and processActivity
	// because processActivity was only called by restaurantActivity and was thus redundant.
	public void restaurantActivity()
	{
		try {
			String activity;
			Scanner sc = new Scanner(System.in);
			int tableNo;
			char serviceCode;
			boolean isOK;

         Table curTable = this.askForTable();
         if (curTable == null) return; // user cancelled
			
			// Display the status of this table
			System.out.println(curTable.toString());
			
			// What are the valid actions for this table?
			String validActions = "";
			String actionDesc = "";
			if (curTable.canParty()) {
				validActions += "P";
				actionDesc += "/ 'P'arty arrival";
			}
			if (curTable.canOrder()) {
				validActions += "O";
				actionDesc += "/ 'O'rder";
			}
			if (curTable.canServe()) {
				validActions += "S";
				actionDesc += "/ 'S'erve";
			}
			if (curTable.canCheck()) {
				validActions += "C";
				actionDesc += "/ get 'C'heck";
			}
			
			// Repeat until we get a valid service code (or cancel)
			do {
				System.out.println("Possible actions " + actionDesc);
				System.out.print("Enter service code (blank=cancel): ");
				String tmpStr = sc.nextLine();
				
				if (tmpStr.equals("")) return;

				serviceCode = tmpStr.toUpperCase().charAt(0);
				isOK = validActions.contains(Character.toString(serviceCode));
				if (!isOK) {
					// Not a valid option
					System.out.println("\nInvalid service code.");
				}
			} while (!isOK);

			// We have a table and a service code.
			switch (serviceCode) {
				case 'P':
					// Party arrives
					//Arrival thisArrival = new Arrival(tableNo);
					curTable.Arrives();
			   break;
				case 'O':
					// Get menu items
					//Order thisOrder = new Order(tableNo);
					curTable.Orders();
			   break;
				case 'S':
					// Food is served
					//Service thisService = new Service(tableNo);
					curTable.Serves();
			   break;
				case 'C':
					// Give the check and bid adieu
					//Check thisCheck = new Check(tableNo);
					curTable.Checks();
			   break;
			}
		}
		catch(Exception ex)
		{
			System.out.println("Caught message " + ex.getMessage());
		}
	} // end restaurantActivity
	

} // End class Restaurant