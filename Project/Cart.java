package Project;

public class Cart {
	
	int userId;
    int productId;
    int quantity;

    public Cart(int userId, int productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    
    public int getProductId() 
    { 
    	return productId; 
    }
    public int getQuantity() 
    { 
    	return quantity; 
    }


}
