package Project;

import java.util.Date;

public class SalesOrder {
	
	 int id;
	 String salesOrderId;
	 Date dateOfOrder;
	 double totalAmount;
	 int userId;

	 public SalesOrder(int id, String salesOrderId, Date dateOfOrder, double totalAmount, int userId) {
	     this.id = id;
	     this.salesOrderId = salesOrderId;
	     this.dateOfOrder = dateOfOrder;
	     this.totalAmount = totalAmount;
	     this.userId = userId;
	    }

	    
	    public String getSalesOrderId() 
	    {
	    	return salesOrderId;
	    }
	    public int getUserId() 
	    { 
	    	return userId; 
	    }

}
