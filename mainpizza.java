
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mainpizza {
    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    List<pizza> orderList = new ArrayList<>();

    System.out.println("----Welcome Pizzaria RenatoKings----" +
                        "\nWe have Peperroni, Margarita, Chicken and Mussarela"+
                        "\nSmall size 12$ // Medium size 16$ // Large size 18$"+
                        "\nEach Topping add 2$");



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
        pizza pizza = new pizza(flavourString, qnty, size);


        //Check to customer about toppings
        System.out.println("Would like to add CHEESE topping?(Yes/No) ");
        String cheeeseCheck = scanner.nextLine();

        if (cheeeseCheck.equalsIgnoreCase("yes")) {
            pizza.addChese();
            
        }

        System.out.println("Would like to add MUSHROOM topping?(Yes/No) ");
        String mushroomCheck = scanner.nextLine();

        if (mushroomCheck.equalsIgnoreCase("yes")) {
            pizza.addMushroom();
            
        }

        //put it at List 
        orderList.add(pizza);
        pizza.displayDetails();

        System.out.println("Would like to add new pizza to order? (Yes/No)");
        String morePizzaCheck = scanner.nextLine();

        if (morePizzaCheck.equalsIgnoreCase("no")) {
            System.out.println();
            pizza.preparing();
            pizza.eating();
            System.out.println();
            break;            
        }

        }  



        //checking list and make price
        double totalPrice = 0;
        for (pizza pizz : orderList) {
            totalPrice += pizz.getPricePizza();
            
        }

        //display total 

        System.out.println("Your order was : ");
        
        for (pizza pizzaListFinal : orderList) {
              System.out.println(pizzaListFinal + "\n");  
        }


       System.out.println("Total $ " + totalPrice);
        scanner.close();





      
                        

    }

    
}
