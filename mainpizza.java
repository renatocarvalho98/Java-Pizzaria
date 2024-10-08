package JavaPizzaria;


import JavaPizzaria.DataBaseOperations.DAO.CustomerDAO;
import JavaPizzaria.DataBaseOperations.DAO.PizzaDAO;
import JavaPizzaria.DataBaseOperations.DBsetup.SetUpDB;
import JavaPizzaria.DataBaseOperations.Service.PizzaService;
import JavaPizzaria.PizzaSetting.Customer.Customer;
import JavaPizzaria.PizzaSetting.PizzaDetails.Pizza;

import java.sql.SQLException;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class mainpizza {

    static CustomerDAO customerDAO = new CustomerDAO();
    static Customer currentCustomer = null;
    static PizzaService pizzaService = new PizzaService();
    
 
    public static void main(String[] args) throws SQLException {

    Scanner scanner = new Scanner(System.in);
    List<Pizza> orderList = new ArrayList<>();
    
    int currentIDSales = PizzaDAO.getCurrentMaxSalesId() +1;
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
        boolean loginSuccess = false;
        while (!loginSuccess) {
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
                                currentCustomer = customer;
                                break;
                                }
                            }
                            loginSuccess = true;
                        } else {
                            System.out.println("Invalid login or password. Would you like to try again or go back to the main menu? (try again/back)");
                            String retryOption = scanner.nextLine();
                            if (retryOption.equalsIgnoreCase("back")) {
                                break;
                            }
                        }
                    }
                    exit = true;
                    break;

        case 2:
        boolean accountCreated = false;
        while (!accountCreated) {
                    System.out.println("Please type your name: ");
                    String nameAccount = scanner.nextLine();
                    System.out.println("Please type your login: ");
                    String nameLogin = scanner.nextLine();
                    System.out.println("Please type your password: ");
                    String passwordLogin = scanner.nextLine();
                    System.out.println("Please type your address: ");
                    String addressLogin = scanner.nextLine();

                        if (customerDAO.checkLogin(nameLogin, passwordLogin) == -1) {
                            currentCustomer = new Customer(nameAccount, nameLogin, passwordLogin, addressLogin);
                            try {
                                customerDAO.insertCustomer(currentCustomer);
                                System.out.println("Account created successfully.");
                                accountCreated = true;
                                checkLoginAfterCreat = 1;
                            } catch (SQLException e) {
                                e.printStackTrace();
                                System.out.println("Error creating account.");
                            }
                        } else {
                            System.out.println("Login already in use. Please choose another login. Would you like to go back to the main menu? (yes/no)");
                            String retryOption = scanner.nextLine();
                            if (retryOption.equalsIgnoreCase("yes")) {
                                break;
                            }
                        }
                    }
                    exit = true;
                    break;
        
        case 3:
        System.out.println("Please type your name: ");
                String nameLoginEmpty = scanner.nextLine();
                System.out.println("Please type your address: ");
                String addressLoginEmpty = scanner.nextLine();

                currentCustomer = new Customer(9999,nameLoginEmpty, "noLogin", "noPassword", addressLoginEmpty);
                exit = true; // Exit after choosing this option
                exit = true;
                break;


        default:
                    System.out.println("Invalid input.");
                    break;
    
     }
 
        }
     
        int currentSalesId = 0;
        try {
            currentSalesId = PizzaDAO.getCurrentMaxSalesId() + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }



        boolean exit2 = false;
        while (!exit2) {

            if (checkLoginAfterCreat == 1) {
                boolean loginSuccess = false;
            while (!loginSuccess) {
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
                                currentCustomer = customer;
                                break;
                                }
                            }
                            loginSuccess = true;
                        } else {
                            System.out.println("Invalid login or password. Would you like to try again or go back to the main menu? (try again/back)");
                            String retryOption = scanner.nextLine();
                            if (retryOption.equalsIgnoreCase("back")) {
                                break;
                            }
                        }
                    }
                    exit2 = true;
                    break;

            } else {
                exit2 = true;
                    break;

            }
        }


    while (true) {



        //flavor
        String flavourString;
        while (true) {
            System.out.println("Tell us which pizza you would like to order ?" +
            "\nPeperroni, Margarita, Chicken or Mussarela");
            flavourString = scanner.nextLine();
            if (flavourString.equalsIgnoreCase("Peperroni") || flavourString.equalsIgnoreCase("Margarita") || flavourString.equalsIgnoreCase("Chicken") || flavourString.equalsIgnoreCase("Mussarela")) {
                break;
            } else {
                System.out.println("Invalid pizza. Please, tapy Peperroni, Margarita, Chicken or Mussarela. ");
            }
        }


        //size
        String size;
        while (true) {
            System.out.println("Excellent! Now tell us the size:  Small / Medium / Large ");
            size = scanner.nextLine();
            if (size.equalsIgnoreCase("small") || size.equalsIgnoreCase("medium") || size.equalsIgnoreCase("large")) {
                break;
            } else {
                System.out.println("Invalid size. please, tapy 'small', 'medium' ou 'large'.");
            }
        }


        System.out.println("How many pizza " + flavourString + " " + size + " you would like to order ?");
        int qnty = scanner.nextInt();
        scanner.nextLine();



 
        //Creat order to pizza    
        Pizza pizza = new Pizza(flavourString, qnty, size);
        pizza.setCustomerId(currentCustomer.getCustomerId());
        pizza.setId(currentSalesId);
       
        
        pizza.calculatePrice();



        //toppings
        while (true) {
            System.out.println("Would you like to add a topping? (Yes/No)");
            String toppingCheck = scanner.nextLine();
            if (toppingCheck.equalsIgnoreCase("yes")) {
                System.out.println("Enter topping name:");
                String topping = scanner.nextLine();
                pizza.addTopping(topping);
            } else {
                break;
            }
        }
       
        

        System.out.println("Any comments for your order?");
        String comments = scanner.nextLine();
        pizza.setComments(comments);
        orderList.add(pizza);
        pizzaService.addPizza(pizza);


        System.out.println("Would like to add new pizza to order? (Yes/No)");
        String morePizzaCheck = scanner.nextLine();
    

        if (morePizzaCheck.equalsIgnoreCase("no")) {
            
            System.out.println();
            System.out.println();

            break;            
        }
    
        }  


        //checking list and make price
        double totalPrice = 0;
        for (Pizza pizz : orderList) {
            totalPrice += pizz.getPrice();
        
       }

      
        //display total 

        System.out.println("Your order was : ");
        
        for (Pizza pizzaListFinal : orderList) {
              System.out.println(pizzaListFinal + "\n");  
        }


       System.out.println("Total $ " + totalPrice);
       scanner.close();

       

    }

    


    
}
