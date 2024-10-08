package JavaPizzaria;

import JavaPizzaria.DataBaseOperations.DAO.CustomerDAO;
import JavaPizzaria.DataBaseOperations.DAO.PizzaDAO;
import JavaPizzaria.DataBaseOperations.DBsetup.SetUpDB;
import JavaPizzaria.DataBaseOperations.Service.PizzaService;
import JavaPizzaria.LoginService.LoginSettings;
import JavaPizzaria.PizzaOrders.PizzaOrder;
import JavaPizzaria.PizzaSetting.Customer.Customer;
import JavaPizzaria.PizzaSetting.PizzaDetails.Pizza;

import java.sql.SQLException;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mainMenu {

    public static void main(String[] args) throws SQLException {

    LoginSettings loginService = new LoginSettings();
    PizzaOrder pizzaOrder = new PizzaOrder();

    Scanner scanner = new Scanner(System.in);
    Customer currentCustomer = null;
    int checkLoginAfterCreat = 0;

    //Show initial Menu
    System.out.println("----Welcome Pizzaria RenatoKings----" +
                        "\nWe have Peperroni, Margarita, Chicken and Mussarela"+
                        "\nSmall size 12$ // Medium size 16$ // Large size 18$"+
                        "\nEach Topping add 2$");

                        
    //Here we gonna setup the adress and Login, if the user dont wanna setup costumer_ID it goes to 01 ID - noLogin
    boolean exit = false;
    while (!exit) {
        System.out.println("You would like to login press - 1 // Creat your new login press - 2 // Without login press - 3 ");
        int optLogin = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
    


    switch (optLogin) {

        case 1:
        currentCustomer = loginService.login(scanner);
        break;

        case 2:
        currentCustomer = loginService.creatAccount(scanner);
        checkLoginAfterCreat = 1;
        break;
        
        case 3:
            System.out.println("Please type your name: ");
            String nameLoginEmpty = scanner.nextLine();
            System.out.println("Please type your address: ");
            String addressLoginEmpty = scanner.nextLine();

            currentCustomer = new Customer(9999,nameLoginEmpty, "noLogin", "noPassword", addressLoginEmpty);
        break;


        default:
        System.out.println("Invalid input.");
        break;

    
     }
     //after creat login, you need login againt to valited your new login and get your customerID
     if (checkLoginAfterCreat == 1) {
        currentCustomer = loginService.login(scanner);
        
     }


     if (currentCustomer != null) {

        int currentSalesId = 0;
        try {
            currentSalesId = PizzaDAO.getCurrentMaxSalesId() + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pizzaOrder.creatOrder(scanner, currentSalesId, currentCustomer.getCustomerId());
        pizzaOrder.displayOrderSummary();
        exit = true;
    }




    scanner.close();
    
}


    }

}
