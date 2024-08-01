package JavaPizzaria.DataBaseOperations.DAO;

import JavaPizzaria.DataBaseOperations.DBsetup.DataBadeDetails;
import JavaPizzaria.PizzaSetting.Customer.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends DataBadeDetails {
    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO Customers (name, login, password, delivery_address) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM Customers";

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
}
