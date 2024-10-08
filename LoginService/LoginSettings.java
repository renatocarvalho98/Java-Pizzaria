package JavaPizzaria.LoginService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import JavaPizzaria.DataBaseOperations.DAO.CustomerDAO;
import JavaPizzaria.PizzaSetting.Customer.Customer;

public class LoginSettings {

    static CustomerDAO customerDAO = new CustomerDAO();
    static Customer currentCustomer = null;


    //where we can connect your login 
    public Customer login(Scanner scanner)throws SQLException{

        System.out.println("Please type your login: ");
        String checkLogin = scanner.nextLine();
        System.out.println("Please type your password: ");
        String checkPassword = scanner.nextLine();


        int userID = CustomerDAO.checkLogin(checkLogin, checkPassword);
        if (userID != -1) {
        System.out.println("Login successful.");
            List<Customer> customers = customerDAO.selectAllCustomers();
                for (Customer customer : customers) {
                    if (customer.getCustomerId() == userID) {

                            return customer;
                            }
                            }
                          
                        } else {
                            System.out.println("Invalid login or password. Would you like to try again or go back to the main menu? (try again/back)");
                        
                               
                            }

        return null;


    }

    //Creat a new Account to your dataBase
    public Customer creatAccount(Scanner scanner)throws SQLException{

        System.out.println("Please type your name: ");
        String nameAccount = scanner.nextLine();
        System.out.println("Please type your login: ");
        String nameLogin = scanner.nextLine();
        System.out.println("Please type your password: ");
        String passwordLogin = scanner.nextLine();
        System.out.println("Please type your address: ");
        String addressLogin = scanner.nextLine();

            if (customerDAO.checkLogin(nameLogin, passwordLogin) == -1) {

                Customer newCustomer = new Customer(nameAccount, nameLogin, passwordLogin, addressLogin);
                customerDAO.insertCustomer(newCustomer);
                System.out.println("Account created successfully.");
                return newCustomer;
                }
            else {
                System.out.println("Login already in use. Please choose another login");  
            }


            return null;

    }
    
}
