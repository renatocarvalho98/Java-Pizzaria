package JavaPizzaria.DataBaseOperations.DAO;

import JavaPizzaria.DataBaseOperations.DBsetup.DataBadeDetails;
import JavaPizzaria.PizzaSetting.Customer.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends DataBadeDetails {
    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO Customers (name, login, password, delivery_address) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM Customers";
    private static final String CHECK_LOGIN_SQL = "SELECT customer_id FROM Customers WHERE login = ? AND password = ?";
    private static final String GET_CUSTOMER_ID_BY_LOGIN = "SELECT customer_id FROM Customers WHERE login = ?";


    //Insert new user at dataBase
    public void insertCustomer(Customer customer) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER,PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getLogin());
            preparedStatement.setString(3, customer.getPassword());
            preparedStatement.setString(4, customer.getDeliveryAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //CHECK WHEN I ASK IN DATABASE 
    public List<Customer> selectAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("customer_id");
                String name = rs.getString("name");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String deliveryAddress = rs.getString("delivery_address");
                customers.add(new Customer(id, name, login, password, deliveryAddress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }



    //CHECK LOGIN IN DATA BASE
    public static int checkLogin(String login, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        PreparedStatement pstmt = conn.prepareStatement(CHECK_LOGIN_SQL)) {
       pstmt.setString(1, login);
       pstmt.setString(2, password);
       ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                // Return the user_id if the login and password are valid
                return resultSet.getInt("customer_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao validar login e senha.");
        }
    
        // Return -1 if the login and password are not valid
        return -1;
      
      
      
        }




    //Get customer ID by login
    public int getCustomerIdByLogin(String login) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(GET_CUSTOMER_ID_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("customer_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error fetching customer ID by login.");
        }

        return -1;
    }
}
