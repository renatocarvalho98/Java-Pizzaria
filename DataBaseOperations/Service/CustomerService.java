package JavaPizzaria.DataBaseOperations.Service;


import JavaPizzaria.DataBaseOperations.DAO.CustomerDAO;
import JavaPizzaria.PizzaSetting.Customer.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    private CustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = new CustomerDAO();
    }

    public void addCustomer(Customer customer) {
        try {
            customerDAO.insertCustomer(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.selectAllCustomers();
    }
}
