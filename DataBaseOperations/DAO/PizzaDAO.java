package JavaPizzaria.DataBaseOperations.DAO;

import JavaPizzaria.DataBaseOperations.DBsetup.DataBadeDetails;
import JavaPizzaria.PizzaSetting.PizzaDetails.Pizza;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class PizzaDAO extends DataBadeDetails {
    private static final String INSERT_PIZZA_SQL = "INSERT INTO Sales (customer_id, pizza_flavor, size, quantity, toppings, price) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_PIZZAS = "SELECT * FROM Sales";

    public void insertPizza(Pizza pizza) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PIZZA_SQL)) {
                preparedStatement.setInt(1, pizza.getCustomerId());
                preparedStatement.setString(2, pizza.getFlavor());
                preparedStatement.setString(3, pizza.getSize());
                preparedStatement.setInt(4, pizza.getQuantity());
                preparedStatement.setString(5, String.join(",", pizza.getToppings()));
                preparedStatement.setDouble(6, pizza.getPrice());
                preparedStatement.setString(7, pizza.getComments());
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pizza> selectAllPizzas() {
        List<Pizza> pizzas = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL,USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PIZZAS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("sale_id");
                int customerId = rs.getInt("customer_id");
                String flavor = rs.getString("pizza_flavor");
                String size = rs.getString("size");
                int quantity = rs.getInt("quantity");
                String toppings = rs.getString("toppings");
                double price = rs.getDouble("price");
                String comments = rs.getString("comments");
                pizzas.add(new Pizza(id, customerId, flavor, size, quantity, toppings, price, comments));
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        }
        return pizzas;
    

    
}
}