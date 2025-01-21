package Project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ShoppingApp {
	
	static List<User> users = new ArrayList<>();
    static List<Product> products = new ArrayList<>();
    static List<Cart> cart = new ArrayList<>();
    static List<SalesOrder> salesOrders = new ArrayList<>();
    static List<SalesOrderDetail> salesOrderDetails = new ArrayList<>();

    public static void main(String[] args) {
       // initializeData();

        // Start application with user login
        User loggedInUser = login();
        if (loggedInUser != null) {
            System.out.println("Login successful. Welcome, " + loggedInUser.getName() + "!");
            showProducts();
            manageCart(loggedInUser);
        } else {
            System.out.println("Invalid login details.");
        }
    }

    public static void initializeData() {
        users.add(new User(1, "John", "password123", "1234567890", "123 Main St", "New York", "NY", "USA"));
        users.add(new User(2, "Alice", "alicepass", "0987654321", "456 Elm St", "Los Angeles", "CA", "USA"));

        products.add(new Product(1, "Laptop", 500, 70000, 10));
        products.add(new Product(2, "Headphones", 50, 8000, 20));
        products.add(new Product(3, "Smartphone", 300, 50000, 15));
    }

    public static User login() {
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

    public static void showProducts() {
        System.out.println("Available products:");
        for (Product product : products) {
            System.out.println(product.getId() + ". " + product.getName() + " - Rs" + product.getSellPrice());
        }
    }

    public static void manageCart(User loggedInUser) {
        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {
            System.out.println("Enter product ID to add to cart, 'r' to remove last item, or 'q' to quit:");
            input = sc.nextLine();

            if (input.equals("q")) break;

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

    public static void placeOrder(User loggedInUser) {
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
        System.out.println("Total amount: Rs" + totalAmount);
    }

    public static Product findProductById(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }
	

}
