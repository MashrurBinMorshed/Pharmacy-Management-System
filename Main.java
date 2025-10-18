
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Main {
    
    private static final Map<String, User> users = new HashMap<>();
    private static final List<Order> orders = new ArrayList<>();
    private static final List<Payment> payments = new ArrayList<>();
    private static final List<Medicine> inventory = new ArrayList<>();
    
    private static final Scanner scanner = new Scanner(System.in);

    private static final String MEDICINE_FILE = "src/medicines.txt";


    public static void main(String[] args) {
        loadMedicinesFromFile();

        Medicine medicine1 = new Medicine(1, "Paracetamol", 5.99, 100);
        Medicine medicine2 = new Medicine(2, "Ibuprofen", 7.49, 75);
        Medicine medicine3 = new Medicine(3, "Napa", 7.49, 75);
        Medicine medicine4 = new Medicine(4, "Monas", 7.49, 75);

        

        Admin admin = new Admin("admin", "password", "adminName", "shopName", "adminNumber");
        Customer customer = new Customer("user", "password", "address", "01865362374");


        inventory.add(medicine2);
        inventory.add(medicine1);
        inventory.add(medicine4);
        inventory.add(medicine3);
                
        users.put(admin.getUsername(), admin);
        users.put(customer.getUsername(), customer);

      


        System.out.println("Welcome to the Pharmacy Management System!");


        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int mainChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (mainChoice) {
                case 1:
                    signUp();
                    break;
                case 2:
                    signIn();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void signUp() {
        System.out.println("\nSign Up:");
        System.out.println("1. Admin");
        System.out.println("2. Customer");
        System.out.print("Enter your choice: ");
        int userType = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userType == 1) {
            System.out.print("Enter admin name: ");
            String adminName = scanner.nextLine();
            System.out.print("Enter shop name: ");
            String shopName = scanner.nextLine();
            System.out.print("Enter admin contact number: ");
            String adminNumber = scanner.nextLine();

            Admin admin = new Admin(username, password, adminName, shopName, adminNumber);
            users.put(username, admin);
            System.out.println("Admin registered successfully!");
        } else if (userType == 2) {
            System.out.print("Enter customer address: ");
            String address = scanner.nextLine();
            System.out.print("Enter customer phone number: ");
            String phoneNumber = scanner.nextLine();

            Customer customer = new Customer(username, password, address, phoneNumber);
            users.put(username, customer);
            System.out.println("Customer registered successfully!");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void signIn() {
        System.out.println("\nSign In:");
        System.out.println("1. Admin");
        System.out.println("2. Customer");
        System.out.print("Enter your choice: ");
        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            if (roleChoice == 1 && user instanceof Admin) {
                handleAdmin((Admin) user);
            } else if (roleChoice == 2 && user instanceof Customer) {
                handleCustomer((Customer) user);
            } else {
                System.out.println("Invalid username or password for the selected role. Please try again.");
            }
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void handleAdmin(Admin admin) {
        boolean running = true;
        while (running) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Medicine");
            System.out.println("2. Remove Medicine");
            System.out.println("3. Update Medicine");
            System.out.println("4. View Medicines");
            System.out.println("5. View Orders");
            System.out.println("6. View Payments");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addMedicine();
                    writeMedicinesToFile();
                    break;
                case 2:
                    removeMedicine();
                    writeMedicinesToFile();
                    break;
                case 3:
                    updateMedicine();
                    writeMedicinesToFile();
                    break;
                case 4:
                    viewMedicines();
                    
                    break;
                case 5:
                    for (var order : orders) {
                        System.out.println(order);
                    }
                    break;
                case 6:
                    for (var payment : payments) {
                        System.out.println(payment);
                    }
                    break;    
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addMedicine() {
        System.out.print("Enter medicine ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter medicine name: ");
        String name = scanner.nextLine();
        System.out.print("Enter medicine price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter medicine quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Medicine medicine = new Medicine(id, name, price, quantity);
        inventory.add(medicine);
        System.out.println("Medicine added: " + medicine);
    }

    private static void removeMedicine() {
        System.out.print("Enter medicine ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        inventory.removeIf(medicine -> medicine.getId() == id);
        System.out.println("Medicine removed with ID: " + id);
    }

    private static void updateMedicine() {
        System.out.print("Enter medicine ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Medicine medicine : inventory) {
            if (medicine.getId() == id) {
                System.out.print("Enter new name: ");
                String name = scanner.nextLine();
                System.out.print("Enter new price: ");
                double price = scanner.nextDouble();
                System.out.print("Enter new quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                medicine.setName(name);
                medicine.setPrice(price);
                medicine.setQuantity(quantity);
                System.out.println("Medicine updated: " + medicine);
                return;
            }
        }
        System.out.println("Medicine not found with ID: " + id);
    }

    private static void viewMedicines() {
        System.out.println("Inventory: ");
        for (Medicine medicine : inventory) {
            System.out.println(medicine.getName());
        }
    }

    private static void handleCustomer(Customer customer) {
        ArrayList<Medicine> cart = new ArrayList<>();

        System.out.println("Medicine List: ");
        for (Medicine medicine : inventory) {
            System.out.println(medicine);
        }


        boolean running = true;
        while (running) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Add to Cart");
            System.out.println("2. View Cart");
            System.out.println("3. Update Cart");
            System.out.println("4. Pay");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                
                    System.out.print("Enter the name of the medicine to add: ");
                    String medicineName = scanner.nextLine();
                    
                    Medicine medicineToAdd = findMedicineByName(medicineName);
                    if (medicineToAdd != null) {
                        System.out.print("Enter the quantity to add: ");
                        int quantityToAdd = scanner.nextInt();
                        
                        // Check if the quantity is valid
                        if (quantityToAdd > 0 && quantityToAdd <= medicineToAdd.getQuantity()) {
                            for (int i = 0; i < quantityToAdd; i++) {
                                cart.add(medicineToAdd);
                            }
                            medicineToAdd.setQuantity(medicineToAdd.getQuantity() - quantityToAdd);
                            System.out.println(quantityToAdd + " " + medicineName + "(s) added to cart.");
                        } else {
                            System.out.println("Invalid quantity. Please enter a quantity between 1 and " + medicineToAdd.getQuantity() + ".");
                        }
                    } else {
                        System.out.println("Medicine not found.");
                    }
                    break;

                case 2:
                    System.out.println("Cart Contents:");
                    if (cart.isEmpty()) {
                        System.out.println("Your cart is empty.");
                    } else {
                        for (Medicine item : cart) {
                            System.out.println(item.getName() + "-" + item.getPrice() + "Tk.");
                        }
                    }
                    break;
                case 3:
                    if (cart.isEmpty()) {
                        System.out.println("Your cart is empty.");
                    } else {
                        System.out.print("Enter the name of the medicine to update: ");
                        String medicineNameToUpdate = scanner.nextLine();
                        
                        Medicine medicineToUpdate = findMedicineByName(medicineNameToUpdate);
                        if (medicineToUpdate != null) {
                            System.out.print("Enter the new quantity: ");
                            int newQuantity = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            
                            // Check if the new quantity is valid
                            if (newQuantity > 0 && newQuantity <= medicineToUpdate.getQuantity()) {
                                // Clear the existing items of the medicine from the cart
                                cart.removeIf(item -> item.equals(medicineToUpdate));
                                // Add the updated quantity of the medicine to the cart
                                for (int i = 0; i < newQuantity; i++) {
                                    cart.add(medicineToUpdate);
                                }
                                System.out.println("Cart updated.");
                            } else {
                                System.out.println("Invalid quantity. Please enter a quantity between 1 and " + medicineToUpdate.getQuantity() + ".");
                            }
                        } else {
                            System.out.println("Medicine not found in cart.");
                        }
                    }
                    break;

                case 4:
                    // Pay functionality
                    if (cart.isEmpty()) {
                        System.out.println("Your cart is empty. Nothing to pay for.");
                    } else {

                        System.out.println("Cart Contents:");
                        for (Medicine item : cart) {
                            System.out.println(item.getName() + "-" + item.getPrice() + "Tk.");
                        }
                        // Calculate and display the total price
                        double totalPriceToPay = calculateTotalPrice(cart);
                        System.out.println();
                        System.out.println("------------------------------");
                        System.out.println("Total Price: Tk." + totalPriceToPay);
                        
                        // Prompt the user to enter the payment amount
                        System.out.print("Enter the payment amount: Tk.");
                        double paymentAmount = scanner.nextDouble();
                        
                        // Check if the payment amount is sufficient
                        if (paymentAmount >= totalPriceToPay) {
                            // Calculate change
                            double change = paymentAmount - totalPriceToPay;
                            System.out.println("Payment successful. Change: Tk." + change);
                            Order tempOrder = new Order(1, customer, cart, null);
                            orders.add(tempOrder);
                            payments.add(new Payment(1, tempOrder, totalPriceToPay, "cash"));
                            tempOrder = null;
                            // Clear the cart after successful payment
                            cart.clear();
                            System.out.println("Your cart is now empty.");
                        } else {
                            System.out.println("Payment amount is not enough. Please provide sufficient amount.");
                        }
                    }
                    break;

                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        
    }
    
    private static Medicine findMedicineById(int id) {
    for (Medicine medicine : inventory) {
        if (medicine.getId() == id) {
            return medicine;
        }
    }
    return null; 
    }
    private static Medicine findMedicineByName(String name) {
        for (Medicine medicine : inventory) {
            if (medicine.getName().equalsIgnoreCase(name.trim())) {
                return medicine;
            }
        }
        return null; // Return null if medicine with the given name is not found
    }
    private static double calculateTotalPrice(ArrayList<Medicine> cart) {
        double totalPrice = 0;
        for (Medicine item : cart) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }


     
    // private static List<Medicine> getMedicinesFromNames(List<String> names) {
    //     List<Medicine> medicines = new ArrayList<>();
    //     for (String name : names) {
    //         for (Medicine medicine : inventory) {
    //             if (medicine.getName().equalsIgnoreCase(name.trim())) {
    //                 medicines.add(medicine);
    //             }
    //         }
    //     }
    //     return medicines;
    // }

    private static void loadMedicinesFromFile() {
        File file = new File(MEDICINE_FILE);
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] parts = line.split(",");
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int quantity = Integer.parseInt(parts[3]);
                    Medicine medicine = new Medicine(id, name, price, quantity);
                    inventory.add(medicine);
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred while loading medicines from file: " + e.getMessage());
            }
        }
    }

    private static void writeMedicinesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MEDICINE_FILE))) {
            for (Medicine medicine : inventory) {
                writer.write(medicine.getId() + "," + medicine.getName() + "," + medicine.getPrice() + "," + medicine.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing medicines to file: " + e.getMessage());
        }
    }

}
