package JavaPizzaria.DataBaseOperations.DAO;

import JavaPizzaria.DataBaseOperations.DBsetup.DataBadeDetails;
import JavaPizzaria.PizzaSetting.PizzaDetails.Pizza;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class PizzaDAO extends DataBadeDetails {
    private static final String INSERT_PIZZA_SQL = "INSERT INTO Sales (sale_id, customer_id, pizza_flavor, size, quantity, toppings, price, comments) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_PIZZAS = "SELECT * FROM Sales";
    private static final String GET_MAX_SALES_ID = "SELECT MAX(sale_id) AS max_id FROM Sales";

    public void insertPizza(Pizza pizza) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PIZZA_SQL)) {
                preparedStatement.setInt(1, pizza.getId());
                preparedStatement.setInt(2, pizza.getCustomerId());
                preparedStatement.setString(3, pizza.getFlavor());
                preparedStatement.setString(4, pizza.getSize());
                preparedStatement.setInt(5, pizza.getQuantity());
                preparedStatement.setString(6, String.join(",", pizza.getToppings()));
                preparedStatement.setDouble(7, pizza.getPrice());
                preparedStatement.setString(8, pizza.getComments());
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
                int lineItemId = rs.getInt("line_item_id");
                int id = rs.getInt("sale_id");
                int customerId = rs.getInt("customer_id");
                String flavor = rs.getString("pizza_flavor");
                String size = rs.getString("size");
                int quantity = rs.getInt("quantity");
                String toppings = rs.getString("toppings");
                double price = rs.getDouble("price");
                String comments = rs.getString("comments");
                pizzas.add(new Pizza(lineItemId, id, customerId, flavor, size, quantity, toppings, price, comments));
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        }
        return pizzas;
    

    
}
public static int getCurrentMaxSalesId() throws SQLException {
    int maxId = 0;
    try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
         PreparedStatement preparedStatement = connection.prepareStatement(GET_MAX_SALES_ID)) {
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            maxId = rs.getInt("max_id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return maxId;
}



}


