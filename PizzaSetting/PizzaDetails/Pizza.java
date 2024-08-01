package JavaPizzaria.PizzaSetting.PizzaDetails;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private int id;
    private int customerId;
    private String flavor;
    private int quantity;
    private String size;
    private List<String> toppings;
    private double price;
    private String comments;

    // Construtor para criar uma pizza com todos os atributos
    public Pizza(int id, int customerId, String flavor, String size, int quantity, String toppings, double price, String comments) {
        this.id = id;
        this.customerId = customerId;
        this.flavor = flavor;
        this.size = size;
        this.quantity = quantity;
        this.toppings = new ArrayList<>();
        for (String topping : toppings.split(",")) {
            this.toppings.add(topping.trim());
        }
        this.price = price;
        this.comments = comments;
    }

    // Construtor simplificado
    public Pizza(String flavor, int quantity, String size) {
        this(0, 0, flavor, size, quantity, "", 0, "");
    }

    // Métodos de acesso e mutação
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void addTopping(String topping) {
        this.toppings.add(topping);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    // Método para calcular o preço
    public void calculatePrice() {
        this.price = this.size.equalsIgnoreCase("small") ? 12 : this.size.equalsIgnoreCase("medium") ? 16 : 18;
        this.price += this.toppings.size() * 2;
        this.price *= this.quantity;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "flavor='" + flavor + '\'' +
                ", quantity=" + quantity +
                ", size='" + size + '\'' +
                ", toppings=" + toppings +
                ", price=" + price +
                ", comments='" + comments + '\'' +
                '}';
    }
}