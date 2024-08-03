package JavaPizzaria.DataBaseOperations.Service;

import java.sql.SQLException;
import java.util.List;

import JavaPizzaria.DataBaseOperations.DAO.PizzaDAO;
import JavaPizzaria.PizzaSetting.PizzaDetails.Pizza;

public class PizzaService {
    
    private PizzaDAO pizzaDAO;

    public PizzaService() {
        this.pizzaDAO = new PizzaDAO();
    }

    public void addPizza(Pizza pizza) {
        try {
            pizzaDAO.insertPizza(pizza);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pizza> getAllPizzas() {
        return pizzaDAO.selectAllPizzas();
    }

    
}
