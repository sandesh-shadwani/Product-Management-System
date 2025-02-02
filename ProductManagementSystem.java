import java.util.*;

class Product {
    public String name;
    public double price;
    public int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Price: " + price + ", Quantity: " + quantity;
    }
}

public class ProductManagementSystem {

    private static HashMap<String, Product> productMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nProduct Management System");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Search Product");
            System.out.println("4. Delete Product");
            System.out.println("5. List All Products");
            System.out.println("6. Sort Products");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    searchProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    listAllProducts();
                    break;
                case 6:
                    sortProducts();
                    break;
                case 7:
                    System.out.print("Are you sure you want to exit? (yes/no): ");
                    String confirmExit = scanner.nextLine();
                    if (confirmExit.equalsIgnoreCase("yes")) {
                        System.out.println("Exiting... Thank you!");
                        return;
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addProduct() {
        try {
            System.out.print("Enter product name: ");
            String name = scanner.nextLine();
            if (productMap.containsKey(name.toLowerCase())) {
                System.out.println("Product already exists. Use update instead.");
                return;
            }

            System.out.print("Enter product price: ");
            double price = Double.parseDouble(scanner.nextLine());
            if (price < 0) {
                System.out.println("Price must be non-negative.");
                return;
            }

            System.out.print("Enter product quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            if (quantity < 0) {
                System.out.println("Quantity must be non-negative.");
                return;
            }

            Product product = new Product(name, price, quantity);
            productMap.put(name.toLowerCase(), product);
            System.out.println("Product added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding product. Please try again.");
        }
    }

    private static void updateProduct() {
        try {
            System.out.print("Enter product name to update: ");
            String name = scanner.nextLine();
            Product product = productMap.get(name.toLowerCase());

            if (product != null) {
                System.out.println("1. Update Name");
                System.out.println("2. Update Price");
                System.out.println("3. Update Quantity");
                System.out.print("Enter the attribute you want to update: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        productMap.remove(name.toLowerCase());
                        product.name = newName;
                        productMap.put(newName.toLowerCase(), product);
                        System.out.println("Product name updated successfully!");
                        break;
                    case 2:
                        System.out.print("Enter new price: ");
                        double newPrice = Double.parseDouble(scanner.nextLine());
                        if (newPrice < 0) {
                            System.out.println("Price must be non-negative.");
                            break;
                        }
                        product.price = newPrice;
                        System.out.println("Product price updated successfully!");
                        break;
                    case 3:
                        System.out.print("Enter new quantity: ");
                        int newQuantity = Integer.parseInt(scanner.nextLine());
                        if (newQuantity < 0) {
                            System.out.println("Quantity must be non-negative.");
                            break;
                        }
                        product.quantity = newQuantity;
                        System.out.println("Product quantity updated successfully!");
                        break;
                    default:
                        System.out.println("Invalid choice. Update failed.");
                }
            } else {
                System.out.println("Product not found.");
            }
        } catch (Exception e) {
            System.out.println("Error updating product. Please try again.");
        }
    }

    private static void searchProduct() {
        try {
            System.out.print("Enter product name to search: ");
            String name = scanner.nextLine();
            Product product = productMap.get(name.toLowerCase());

            if (product != null) {
                System.out.println("Product found: " + product);
            } else {
                System.out.println("Product not found.");
            }
        } catch (Exception e) {
            System.out.println("Error searching product. Please try again.");
        }
    }

    private static void deleteProduct() {
        try {
            System.out.print("Enter product name to delete: ");
            String name = scanner.nextLine();
            Product product = productMap.remove(name.toLowerCase());

            if (product != null) {
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("Product not found.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting product. Please try again.");
        }
    }

    private static void listAllProducts() {
        if (productMap.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Product List:");
            for (Product product : productMap.values()) {
                System.out.println(product);
            }
        }
    }

    private static void sortProducts() {
        if (productMap.isEmpty()) {
            System.out.println("No products to sort.");
            return;
        }

        List<Product> productList = new ArrayList<>(productMap.values());
        System.out.println("Sort by:");
        System.out.println("1. Name");
        System.out.println("2. Price");
        System.out.println("3. Quantity");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                productList.sort(Comparator.comparing(product -> product.name.toLowerCase()));
                break;
            case 2:
                productList.sort(Comparator.comparingDouble(product -> product.price));
                break;
            case 3:
                productList.sort(Comparator.comparingInt(product -> product.quantity));
                break;
            default:
                System.out.println("Invalid choice. Sorting cancelled.");
                return;
        }

        System.out.println("Sorted Products:");
        for (Product product : productList) {
            System.out.println(product);
        }
    }
}
