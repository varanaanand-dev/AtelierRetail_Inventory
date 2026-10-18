import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Main {
    //to actually save what the user changes in our system
    public static void saveInventory(ArrayList<Product> inventory) {
        try{
            FileWriter writer = new FileWriter("inventory.txt");
            for (Product product : inventory) {
                writer.write(product.name + "| " + product.price + "| " + product.category +  "| "+ product.collection +  "| "+ product.stock + "\n");
            }
            writer.close();
        }
        catch(IOException e) {
            System.out.println("ERROR: could not save inventory");
        }
    }
    //to load the saved file back INTO the arraylist
    public static void loadInventory (ArrayList<Product> inventory) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader("inventory.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split("\\|");
                //to get string back to double
                String stringPrice = parts[1].trim();
                double doublePrice = Double.parseDouble(stringPrice);

                //to get string back to int
                String stringStock = parts[4].trim();
                int numStock = Integer.parseInt(stringStock);

                //creates and adds the saved product
                Product addingNewProduct = new Product(parts[0].trim(), doublePrice, parts[2].trim(), parts[3].trim(), numStock);
                inventory.add(addingNewProduct);

                line = reader.readLine();

            }
            reader.close();
        }
        catch (IOException e){
            System.out.println("ERROR: could not load inventory");
        }
    }



    public static void main(String[]args) {
        //putting the products into a list- arraylist so its easy and don't have to hardcode a list
        ArrayList <Product> inventory = new ArrayList<>();
        loadInventory(inventory);

        Scanner scanner = new Scanner(System.in);//to get user input into the program

        boolean running = true;
        //menu
            while(running) {
                System.out.println("WELCOME TO ATELIER");
                System.out.println("1. View Inventory" );
                System.out.println("2. View Low Stock" );
                System.out.println("3. Sell Product" );
                System.out.println("4. Restock Product" );
                System.out.println("5. Add Product" );
                System.out.println("6. Delete Product");
                System.out.println("7. Exit" );
                System.out.println("Select an option(use numbers): " );
                int choice = scanner.nextInt();

                if (choice == 1) {
                    System.out.println("INVENTORY-");
                    System.out.println(("ATELIER currently has ") + inventory.size() + (" products"));
                    for (Product product : inventory) {
                        System.out.println(product.name + "| $" + product.price + "| Stock: " + product.stock);

                    }
                    //back to menu logic enforcing user to type only 0
                    System.out.println("To go back to Menu type 0: " );
                    int menuButton= scanner.nextInt();
                    while (menuButton != 0) {
                        System.out.println("Type 0 to get to menu");
                        menuButton= scanner.nextInt();
                    }


                } else if (choice == 2) {
                    System.out.println("Item low in stock- ");
                    for (Product product : inventory) {
                        if (product.stock < 13) {
                            System.out.println(product.name);
                        }
                    }
                    //back to menu logic enforcing user to type only 0
                    System.out.println("To go back to Menu type 0: " );
                    int menuButton= scanner.nextInt();
                    while (menuButton != 0) {
                        System.out.println("Type 0 to get to menu");
                        menuButton= scanner.nextInt();
                    }


                }
                else if(choice == 3){
                    int number = 1;
                    for (Product product : inventory) {
                        System.out.println(number + ". "+ product.name + "| Stock: "+product.stock );
                        number++;
                    }
                    //what product to sell
                    System.out.println("Select product: ");
                    int productChoice = scanner.nextInt();
                    //checking if productchoice number is valid or not...
                    // if vaild move out the loop and continue function, if not then re-enter vaild number
                    while (productChoice < 1 || productChoice > inventory.size())
                    {
                        System.out.println("Please enter Product Choice 1 - " +inventory.size() + ": ");
                        productChoice = scanner.nextInt();
                    }
                    Product selectedProduct = inventory.get(productChoice -1);
                    System.out.println("Product Selected: " +selectedProduct.name);

                    //how much of the selected product to sell
                    System.out.println("How many products to sell? ");
                    int productSelling = scanner.nextInt();
                    System.out.println("Selling Quantity: " +productSelling);
                    selectedProduct.sell(productSelling);
                    saveInventory(inventory);

                    //back to menu logic enforcing user to type only 0
                    System.out.println("To go back to Menu type 0: " );
                    int menuButton= scanner.nextInt();
                    while (menuButton != 0) {
                        System.out.println("Type 0 to get to menu");
                        menuButton= scanner.nextInt();
                    }


                }
                else if(choice == 4) { //restock product
                    int number = 1;
                    for (Product product : inventory) {
                        System.out.println(number + ". "+ product.name + "| Stock: "+product.stock );
                        number++;
                    }
                    //select which product to restock
                    System.out.println("Select product to restock: ");
                    int productChoice = scanner.nextInt();
                    while (productChoice < 1 || productChoice > inventory.size())
                    {
                        System.out.println("Please enter Product Choice 1 - " +inventory.size() + ": ");
                        productChoice = scanner.nextInt();
                    }
                    Product selectedProduct = inventory.get(productChoice -1);
                    System.out.println("Product Selected: " +selectedProduct.name + "| Stock: "+selectedProduct.stock);

                    //select how much to restock
                    System.out.println("Restock quantity? ");
                    int restockQuant = scanner.nextInt();
                    System.out.println("Restock Quantity: " +restockQuant);
                    selectedProduct.restock(restockQuant);
                    saveInventory(inventory);


                    //back to menu logic enforcing user to type only 0
                    System.out.println("To go back to Menu type 0: ");
                    int menuButton = scanner.nextInt();
                    while (menuButton != 0) {
                        System.out.println("Type 0 to get to menu");
                        menuButton = scanner.nextInt();
                    }
                }

                else if(choice == 5){ //add product to inventory
                    scanner.nextLine();
                    System.out.println("Product Name: ");
                    String productName = scanner.nextLine();

                    System.out.println("Price: ");
                    double productPrice = scanner.nextDouble();

                    scanner.nextLine();
                    System.out.println("Category: ");
                    String productCategory = scanner.nextLine();

                    System.out.println("Collection Name: ");
                    String productCollection = scanner.nextLine();

                    System.out.println("Stock Quantity: ");
                    int productStock = scanner.nextInt();

                    //Adding new product and putting it into inventory behind the doors
                    Product addingNewProduct = new Product(productName, productPrice, productCategory, productCollection, productStock);
                    inventory.add(addingNewProduct);

                    //save current inventory in file
                    saveInventory(inventory);

                    //product added msg and shortcut or go back to menu
                    System.out.println("Product Successfully Added! " + productName);
                    System.out.println("To view full information type 1, or type 0 to return to Menu.");
                    System.out.println("Enter 1 or 0: ");
                    int shortcut = scanner.nextInt();
                    while (shortcut != 0 && shortcut != 1 ) {
                        System.out.println("Please only Type 0 to get to menu or 1 to inventory");
                        shortcut= scanner.nextInt();
                    }

                    if(shortcut == 1)
                    {
                        System.out.println("INVENTORY-");
                        System.out.println(("ATELIER currently has ") + inventory.size() + (" products"));
                        for (Product product : inventory) {
                            System.out.println(product.name + "| $" + product.price + "| Stock: " + product.stock);
                        }
                        System.out.println("To go back to Menu type 0: ");
                        //back to menu logic enforcing user to type only 0
                        int menuButton = scanner.nextInt();
                        while (menuButton != 0 ) {
                            System.out.println("Type 0 to get to menu");
                            menuButton = scanner.nextInt();
                        }

                    }
                    else if(shortcut == 0) {
                        //goes back to menu
                    }

                }  else if (choice == 6) {
                    //delete product
                    System.out.println("Delete Product- ");
                    //print numbered list
                    int number = 1;
                    for (Product product : inventory) {
                        System.out.println(number + ". "+ product.name + "| Stock: "+product.stock );
                        number++;

                    }



                }
                else if (choice == 7) {
                    System.out.println("Goodbye!");
                    running = false;
                }
                else {
                    System.out.println("Invalid option. Please choose 1-6");
                }
            }



    }

}