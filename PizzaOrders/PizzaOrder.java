package JavaPizzaria.PizzaOrders;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import JavaPizzaria.DataBaseOperations.Service.PizzaService;
import JavaPizzaria.PizzaSetting.PizzaDetails.Pizza;

public class PizzaOrder {

static PizzaService pizzaService = new PizzaService();
List<Pizza> orderList = new ArrayList<>();

public void creatOrder(Scanner scanner, int currentSalesId, int customerId){
    String flavourString, size;

while (true) {
    



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



    Pizza pizza = new Pizza(flavourString, qnty, size);
    pizza.setCustomerId(customerId);
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

    
}

public void displayOrderSummary() {
    double totalPrice = 0;
    System.out.println("Your order was:");
    for (Pizza pizza : orderList) {
        totalPrice += pizza.getPrice();
        System.out.println(pizza);
    }
    System.out.println("Total: $" + totalPrice);
}

}
