/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import Controller.ProductManagement;
import ObjectData.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Platform;
import utilities.Validation;

/**
 *
 * @author tiozo
 */
public class BikeStoreManagementSystem {

    
    ProductManagement pm;
    
    public BikeStoreManagementSystem() throws Exception {
        this.pm = new ProductManagement();
    }
    
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        String[] options = {"Create a Product",
            "Search Product", "Update Product",
            "Delete Product", "Save",
            "Print All List From File", "Exit"};    
        
        BikeStoreManagementSystem main = new BikeStoreManagementSystem();
        
        main.load();
        
        int choice = 0;
        System.out.println("Product need to have Brand and Category!!!");
        do {
            System.out.println("\nProduct Management Program");
            choice = Menu.getChoice(options); // show Menu options
            switch (choice) {
                case 1:
                    main.createProduct();
                    main.backToMenu();
                    break;
                case 2:
                    main.searchProduct();
                    main.backToMenu();
                    break;
                case 3:
                    main.updateProduct();
                    main.backToMenu();
                    break;
                case 4:
                    main.deleteProduct();
                    main.backToMenu();
                    break;
                case 5:
                    main.saveMain();
                    main.backToMenu();
                    break;
                case 6:
                    main.printAll();
                    main.backToMenu();
                    break;
                default:
                    System.out.println("Good bye!");
            }
        } while (choice > 0 && choice < options.length);
    }
    
    public void createProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.print("[Add Product]\n");
        String id, name, brandId = null, categoryId = null;
        int modelYear;
        Double listedPrice;
        do {
            id = Validation.checkString("Enter Product ID: ");
        } while (pm.isExist(id)); /// check exist;

        name = Validation.checkString("Enter Product's Name: ");
        /// need to check the existing Brand and Category -> done
        /// dropdown 
        List<String> optionsB = new ArrayList<>();
        for (Brand b: pm.listB) {
            optionsB.add((new Brand(b.getId()).getId()));
        }
        System.out.print("Choose new Product Brand ID: \n");
        int choice;
        do {
            choice = Menu.getChoiceFromList(optionsB);
            if (choice >= 1 && choice <= optionsB.size()) {
                brandId = optionsB.get(choice - 1);
                break;
            } else {
                System.out.println("Please input an IN RANGE option !!");
            }
        } while (true);
        


        List<String> optionsC = new ArrayList<>();
        for (Category c: pm.listC) {
            optionsC.add((new Category(c.getId()).getId()));
        }
        System.out.print("Choose new Product Category ID: \n");
        do {
            choice = Menu.getChoiceFromList(optionsC);
            if (choice >= 1 && choice <= optionsC.size()) {
                categoryId = optionsC.get(choice - 1);
                break;
            } else {
                System.out.println("Please input an IN RANGE option !!");
            }
        } while (true);
        
        modelYear = Validation.checkInt("Enter Product's Model Year: ");
        listedPrice = Validation.checkDouble("Enter Product's Listed Price: ");
        
        pm.addItem(new Product(id, name, brandId, categoryId, modelYear, listedPrice));
    }
    
    public void searchProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[Search Product]");
        String required;
        required = Validation.checkString("Input Product Name: ");
        
        List<Product> answer = pm.searchProduct(required);
        
        if (answer == null || answer.isEmpty()) {
            System.out.println("Have no any Product.");
            return;
        } 
        System.out.println("Product Listed: ");
        for (Product e: answer) {
            System.out.printf("Name: %s, Model Year: %d", e.getName(), e.getModelYear());
        }
    };
    
    /*
        Need to check file | implemented
        Need to check for empty changes | done
    */
    public void updateProduct() throws CloneNotSupportedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("[Update Product]");
        
        String pid = Validation.checkString("Enter Product ID: ");
        
        if (pm.getId(pid) == null) {
            System.out.println("Product does not exist !!");
            return;
        }
        Product item = new Product(pm.getId(pid));
        
            System.out.println("Input all the info below, leave empty if wanted to keep the current state.");
            System.out.println("!! Notice 1: If the Brand/Category ID doesn't exist, it will mark as unchanged !!");
            System.out.println("!! Notice 2: If any Numerical input isn't an Number (Integer/real) \n"
                             + "             or has Negative value will also mark as unchanged !!");
            String name, brandId, categoryId;
            String modelYear;
            String listedPrice;
            
            System.out.println("Input new Product name: ");
            name = sc.nextLine().trim();
            if (!name.equals("")) {
                item.setName(name.toUpperCase());
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
            
            /// modify to become a choosing list | done, pack it into a class
            List<String> optionsB = new ArrayList<>();
            for (Brand b: pm.listB) {
                optionsB.add((new Brand(b.getId()).getId()));
            }
            optionsB.add("Keep current.");
            System.out.print("Choose new Product Brand ID: \n");
            int choice = Menu.getChoiceFromList(optionsB);
            if (choice - 1 < optionsB.size() - 1) {
                item.setBrandIdx(pm.listB.get(choice - 1).getId());
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
                

            List<String> optionsC = new ArrayList<>();
            for (Category c: pm.listC) {
                optionsC.add((new Category(c.getId()).getId()));
            }
            optionsC.add("Keep current.");
            System.out.print("Choose new Product Category ID: \n");
            choice = Menu.getChoiceFromList(optionsC);
            if (choice - 1 < optionsC.size() - 1) {
                item.setCategoryIdx(pm.listC.get(choice - 1).getId());
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
            
            /// check if larger then current year
            System.out.print("Input new model year: ");
            modelYear = (sc.nextLine().trim());
            if (!modelYear.equals("") && Validation.isNumeric(modelYear)) {
                item.setModel(Integer.parseInt(modelYear));
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
            
            System.out.print("Input new list price: ");
            listedPrice = sc.nextLine().trim();
            if (!listedPrice.equals("") && Validation.isNumeric(listedPrice)) { 
                item.setListedPrice(Double.parseDouble(listedPrice));
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
            
            if (pm.updateItem(item)) {
                System.out.println("Product Updated");
            } else {
                System.out.println("Product failed to Update");
            }
            /// Still haven't checked for the Invalid String. (Brand ID, category ID) is in File or not -> checked
            /// after adding that function create a flag varible to state the success or fail -> checked
        
    };
    public void deleteProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[Delete Product]");
        String id = Validation.checkString("Input the ID: ");
        if (pm.getId(id) == null) {
            System.out.println("Product does not exist");
        } else {
            System.out.println("Please Enter [Yes] to delete the Product. |Case sentitive|");
            String answer = sc.nextLine().trim();
            if (answer.equals("Yes")) {
                pm.deleteItem(pm.getId(id));
                System.out.println("Product deleted !!");
            } else {
                System.out.println("Confirmation failed.");
            }
        }
    };
    public void saveMain() throws Exception {
        System.out.println("[Saving to files]");
        try {
            pm.save();
        } catch (Exception e) {
            throw e;
        }
    };
    public void printAll() {
        System.out.println("[Printing all Product]");
        pm.printAll();
    };
    
    public void load() throws Exception {
        pm.load();
    }
    
    public void backToMenu() {
        System.out.println("Back to menu ? [Yes] : [No]");
        Scanner sc = new Scanner(System.in);
        String ans = sc.nextLine().trim().toUpperCase();
        if (ans.equals("No")) {
            System.out.println("Exiting");
            Platform.exit();
        } else {
            System.out.println("Returning to main menu");
        }
    }
}
