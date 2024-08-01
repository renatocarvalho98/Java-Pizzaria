package JavaPizzaria;

import JavaPizzaria.DataBaseOperations.DBsetup.SetUpDB;
import JavaPizzaria.PizzaSetting.PizzaDetails.Pizza;

//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class mainpizza {
    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    List<Pizza> orderList = new ArrayList<>();

    System.out.println("----Welcome Pizzaria RenatoKings----" +
                        "\nWe have Peperroni, Margarita, Chicken and Mussarela"+
                        "\nSmall size 12$ // Medium size 16$ // Large size 18$"+
                        "\nEach Topping add 2$");

                        try {
                            if (SetUpDB.setupDBDetails()) {
                                System.out.println("Database login and table created");
                            } else {
                                System.out.println("Oh no! There was a database creation problem...");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }



    while (true) {

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

        //put it at List 
        orderList.add(pizza);
        
        

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
