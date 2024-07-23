

public class pizza {

    private String flavor;
    private int qnty;
    private String size;
    private double pricePizza;
    private double basePricePizza;
    private boolean hasChesse;
    private boolean hasMushroom;




    public pizza(String flavor, int qnty, String size) {
        this.flavor = flavor;
        this.qnty = qnty;
        this.size = size;
        this.hasChesse = false;
        this.hasMushroom = false;
        setBasePricePizza(size);
        calculatePrice();
    }
 

    public void setBasePricePizza(String size) {
        this.size = size;
        switch (this.size) {
            case "small":
            this.basePricePizza = 12;
            break;

            case "medium":
            this.basePricePizza = 16;
            break;

            case "large":
            this.basePricePizza = 18;
            break;
        
            default:
            System.out.println("No pizza this size! Only small, medium, large");
                break;
        }
    }


    public void addChese(){
        this.hasChesse = true;
        

    }

    public void addMushroom(){
        this.hasMushroom = true;

    }

    public void calculatePrice(){
        this.pricePizza = this.basePricePizza * this.qnty;
        if (this.hasChesse) {
            this.pricePizza += 2 * this.qnty;
            
        } 
        if (this.hasMushroom) {
            this.pricePizza += 2 * this.qnty;
            
        }
    }



    public String getFlavor() {
        return flavor;
    }


    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }


    public int getQnty() {
        return qnty;
    }


    public void setQnty(int qnty) {
        this.qnty = qnty;
    }


    public String getSize() {
        return size;
    }


    public double getPricePizza() {
        return pricePizza;
    }


    public boolean isHasChesse() {
        return hasChesse;
    }


    public boolean isHasMushroom() {
        return hasMushroom;
    }





    //display pizza 

    public void displayDetails(){
        System.out.println("Flavor " + flavor +
                "\nQuantity " + qnty +
                "\nSize " + size +                 
                "\nCheese " + (hasChesse ? "Yes" : "No") +
                "\nMushroom " + (hasMushroom ? "Yes" : "No") +
                "\nPrice " + pricePizza );


    }

    //eat void
    public void eating() {
        System.out.println("Eat a pizza slowly, it can choke you if you eat it too fast!");
    }
    
    //preparing pizza
    public void preparing(){
        System.out.println("We are making your pizza, be ready to take it");
    }


    @Override
    //method toString to return pizza orders
    public String toString(){
        return "Flavor " + flavor +
                "\nQuantity " + qnty +
                "\nSize " + size +                 
                "\nCheese " + (hasChesse ? "Yes" : "No") +
                "\nMushroom " + (hasMushroom ? "Yes" : "No") +
                "\nPrice " + pricePizza ;
    }

}
