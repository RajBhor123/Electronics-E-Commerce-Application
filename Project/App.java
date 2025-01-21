package Project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {

	
	List<User> users = new ArrayList<>();
    List<Product> products = new ArrayList<>();
    List<Cart> cart = new ArrayList<>();
    List<SalesOrder> salesOrders = new ArrayList<>();
    List<SalesOrderDetail> salesOrderDetails = new ArrayList<>();

   
    void start() {
        // Start application with user login
    	System.out.println("Welcome to the Application !");
        User loggedInUser = login();
        if (loggedInUser != null) {
            System.out.println("Login successful. Welcome, " + loggedInUser.getName() + "!");
            showProducts();
            manageCart(loggedInUser);
        } else {
            System.out.println("Invalid login details.");
        }
    }

    void Data() {
        users.add(new User(1, "Raj", "Raj123@", "1234567890", "Sai prerana", "Navi Mumbai0", "Maharashtra", "India"));
        users.add(new User(2, "Aftab", "aftabpass", "0987654321", "Om Sai ", "Navi Mumbai", "Maharashtra", "India"));
        users.add(new User(3, "Omkar", "omkar123", "8828388393", "Sai Dham ", "Navi Mumbai", "Maharashtra", "India"));
        
        products.add(new Product(1, "Washing Machine", 35000, 40000, 10));
        products.add(new Product(2, "TeleVision", 400000, 48000, 20));
        products.add(new Product(3, "Iphone 16 Pro Max", 1300000, 1600000, 15));
        products.add(new Product(4, "Refrigerator", 500000, 550000, 15));
        products.add(new Product(5, "Oven", 25000, 30000, 15));
    }

    User login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = sc.nextLine();
        System.out.println("Enter password:");
        String password = sc.nextLine();

        for (User user : users) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    void showProducts() {
        System.out.println("Available products:");
        for (Product product : products) {
            System.out.println(product.getId() + ". " + product.getName() + " - Rs " + product.getSellPrice());
        }
    }

    void manageCart(User loggedInUser) {
        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {
            System.out.println("Enter product ID to add to cart, 'r' to remove last item, or 'q' to quit:");
            input = sc.nextLine();

            if (input.equals("q")) 
            	break;

            if (input.equals("r") && !cart.isEmpty()) {
                cart.remove(cart.size() - 1);
                System.out.println("Last item removed from cart.");
            } else {
                try {
                    int productId = Integer.parseInt(input);
                    System.out.println("Enter quantity:");
                    int quantity = sc.nextInt();
                    sc.nextLine();
                    cart.add(new Cart(loggedInUser.getId(), productId, quantity));
                    System.out.println("Product added to cart.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid product ID. Please enter a valid number.");
                }
            }
        }

        System.out.println("Do you want to place an order? (yes/no)");
        if (sc.nextLine().equalsIgnoreCase("yes")) {
            placeOrder(loggedInUser);
        }
    }
    
    Product findProductById(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    void placeOrder(User loggedInUser) {
        double totalAmount = 0;

        for (Cart cartItem : cart) {
            Product product = findProductById(cartItem.getProductId());
            if (product != null) {
                totalAmount += product.getSellPrice() * cartItem.getQuantity();
            }
        }

        String salesOrderId = "SO" + (salesOrders.size() + 1);
        SalesOrder salesOrder = new SalesOrder(salesOrders.size() + 1, salesOrderId, new Date(), totalAmount, loggedInUser.getId());
        salesOrders.add(salesOrder);

        for (Cart cartItem : cart) {
            Product product = findProductById(cartItem.getProductId());
            if (product != null) {
                SalesOrderDetail orderDetail = new SalesOrderDetail(
                    salesOrders.size(),
                    salesOrderId,
                    new Date(),
                    product.getSellPrice() * cartItem.getQuantity(),
                    loggedInUser.getId(),
                    cartItem.getProductId(),
                    cartItem.getQuantity(),
                    new Date()
                );
                salesOrderDetails.add(orderDetail);
            }
        }

        System.out.println("Order placed successfully! Sales Order ID: " + salesOrderId);
        System.out.println("Total amount: Rs " + totalAmount);
    }


    
    public static void main(String[] args) {
        App app1 = new App();
        app1.Data();
        app1.start();
    }

}
