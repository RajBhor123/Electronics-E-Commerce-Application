package Project;

public class Product {
	
	int id;
    String name;
    double costPrice;
    double sellPrice;
    int quantity;

    public Product(int id, String name, double costPrice, double sellPrice, int quantity) {
        this.id = id;
        this.name = name;
        this.costPrice = costPrice;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
    }

    
    public int getId() 
    { 
    	return id; 
    	
    }
    public String getName() 
    { 
    	return name; 
    }
    
    public double getSellPrice() 
    {
    	return sellPrice; 
    }

}
