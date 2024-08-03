package JavaPizzaria.DataBaseOperations.DBsetup;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SetUpDB extends DataBadeDetails {


     /**
     * Method to set up the details database table.
     * @return true if setup is successful, false otherwise.
     */
    public static boolean setupDBDetails() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        {
            try (Connection conn = DriverManager.getConnection(DB_BASE_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement()) {
                


                stmt.execute("CREATE DATABASE IF NOT EXISTS " + DB_NAME + " ;");
                stmt.execute("USE " + DB_NAME + " ;");
           // Ensure the Customers table exists
           String createCustomersTable = 
               "CREATE TABLE IF NOT EXISTS Customers (" +
               "customer_id INT PRIMARY KEY AUTO_INCREMENT, " +
               "name VARCHAR(255) NOT NULL, " +
               "login VARCHAR(255) NOT NULL, " +
               "password VARCHAR(255) NOT NULL, " +
               "delivery_address VARCHAR(255) NOT NULL" +
               ");";
           stmt.execute(createCustomersTable);

           // Ensure the Sales table exists
           String createSalesTable = 
               "CREATE TABLE IF NOT EXISTS Sales (" +
               "line_item_id INT AUTO_INCREMENT PRIMARY KEY, " +
               "sale_id INT, " +
               "customer_id INT, " +
               "pizza_flavor VARCHAR(255), " +
               "size VARCHAR(50), " +
               "quantity INT, " +
               "toppings VARCHAR(255), " +
               "price DOUBLE, " +
               "comments VARCHAR(255), " +
               "PRIMARY KEY (sale_id, customer_id), " +
               "FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)" +
               ");";
           stmt.execute(createSalesTable);

           System.out.println("Database setup complete.");
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
        return false;
}
        
        
        
        
        
    }
    
    
    
    


    
    
    





    

