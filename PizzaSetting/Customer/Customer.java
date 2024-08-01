package JavaPizzaria.PizzaSetting.Customer;

public class Customer {
    private int customerId;
    private String name;
    private String login;
    private String password;
    private String deliveryAddress;

    public Customer(String name, String login, String password, String deliveryAddress) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.deliveryAddress = deliveryAddress;
    }

    
    // Getters and setters for customer attributes

    public Customer(int customerId, String name, String login, String password, String deliveryAddress) {
        this.customerId = customerId;
        this.name = name;
        this.login = login;
        this.password = password;
        this.deliveryAddress = deliveryAddress;
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}

