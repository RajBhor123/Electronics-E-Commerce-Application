package Project;

import java.util.Date;

public class SalesOrderDetail extends SalesOrder implements TotalAmountCalculator{

	int productId;
    int quantity;
    Date deliveryDate;
	
	public SalesOrderDetail(int id, String salesOrderId, Date dateOfOrder, double totalAmount, int userId,int productId, int quantity, Date deliveryDate) {
		super(id, salesOrderId, dateOfOrder, totalAmount, userId);
		this.productId = productId;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
	}

	@Override
	public double calculateTotalAmount(int quantity, double sellPrice) {
		
		return quantity * sellPrice;
	}

}
