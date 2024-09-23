/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Controller.ramManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Platform;
import objectData.RAM;
import tester.randomRAM;
import utilities.Validation;

/**
 *
 * @author tiozo
 */
public class LaptopRamManagement {

    ramManager rm;
    Scanner sc;
    
    public LaptopRamManagement() throws Exception {
        this.sc = new Scanner(System.in);
        this.rm = new ramManager();
        ///test();
    }
    
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        String[] options = {"Create a RAM",
            "Search RAM", "Update RAM",
            "Delete RAM", "List of existing RAM",
            "Save sessions", "Exit"};    
        
        LaptopRamManagement main = new LaptopRamManagement();
        
        int choice = 0;
        System.out.println("Product need to have Brand and Category!!!");
        do {
            System.out.println("\nProduct Management Program");
            choice = Menu.getChoice(options); // show Menu options
            switch (choice) {
                case 1:
                    main.create();
                    while (main.backToMenu().equalsIgnoreCase("NO")) {
                        main.create();
                    }
                    break;
                case 2:
                    main.search();
                    while (main.backToMenu().equalsIgnoreCase("NO")) {
                        main.search();
                    }
                    break;
                case 3:
                    main.update();
                    break;
                case 4:
                    main.delete();
                    break;
                case 5:
                    main.printAll();
                    break;
                case 6:
                    main.save();
                    break;
                case 7:
                    main.quit();
                    break;
            }
        } while (choice > 0 && choice < options.length);
    }
    
    public void create() {
        System.out.println("[Add RAM]");
        
        String type, bus, brand;
        int quantity;
        String date;
        
        /*
        ask the user all the options, to use an already inputed type or a new one
        */
        
        List<String> options;
        
        System.out.println("Enter a number correspond to the Type you wanted to add.");
        
        options = rm.getAllType();
        options.add("Others.");
        int choice = Menu.getChoiceFromList(options) - 1;
        type = options.get(choice);
        
        if (type.equals("Others.")) {
            type = Validation.checkString("Enter a new Type: ");
        }
        options.clear();
        
        System.out.println("Enter a number correspond to the Bus you wanted to add.");

        options = rm.getAllBus();
        options.add("Others.");
        choice = Menu.getChoiceFromList(options) - 1;
        bus = options.get(choice);
        
        if (bus.equals("Others.")) {
            bus = Validation.checkString("Enter a new Bus: ");
        }
        options.clear();
        
        System.out.println("Enter a number correspond to the Brand you wanted to add.");

        options = rm.getAllBrand();
        options.add("Others.");
        choice = Menu.getChoiceFromList(options) - 1;
        brand = options.get(choice);
        
        if (brand.equals("Others.")) {
            brand = Validation.checkString("Enter a new Brand: ");
        }
        options.clear();
        
        quantity = Validation.checkInt("Input the quantity: ");
        
        int redo = 0;
        do {
            if (redo++ > 0) {
                System.out.println("Please enter with a valid format !");
                System.out.println();
            }
            System.out.print("Enter a date with format MM-yyyy: ");
            date = sc.nextLine().trim();
        } while (Validation.checkDate(date));
        
        RAM item = new RAM("RAM" + type + "_" + rm.getByType(type).size(), type, bus, brand, quantity, date);
        
        if (rm.addItem(item)) {
            System.out.println("RAM added !!!");
        } else 
            System.out.println("RAM failed to add !!!");
    }
    
    public String formatRAM(RAM ram) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-20s", ram.getCode()));
        sb.append(String.format("%-15s", ram.getType() != null ? ram.getType() : ""));
        sb.append(String.format("%-15s", ram.getBrand() != null ? ram.getBrand() : ""));
        sb.append(String.format("%-15s", ram.getBus() != null ? ram.getBus() : ""));
        sb.append(String.format("%-19s", ram.getProduction_month_year()));
        sb.append(String.format("%5d", ram.getQuantity()));

        return sb.toString();
    }
    
    public void printLine(List<RAM> listR) {
        if (listR.isEmpty()) 
            System.out.println("NO ELEMENTS");
        for (RAM e: listR) {
            if (e.getActive())
                System.out.println(formatRAM(e));
        }
    }
    
    public void search() { 
        System.out.println("\n[Search for RAM]");
        
        String[] options = {"by Type", "by Bus", "by Brand"};
        
        List<String> optionsList = new ArrayList();
        int choice = Menu.getChoice(options); // show Menu options
        int idx = 0;
        String id = null; 
        List<RAM> listR = null;
        switch (choice) {
            case 1:
                System.out.println("Enter a number correspond to the Type you wanted to search.");
                optionsList = rm.getAllType();
                idx = Menu.getChoiceFromList(optionsList) - 1;
                listR = rm.getByType(optionsList.get(idx));
                break;
            case 2:
                System.out.println("Enter a number correspond to the Bus you wanted to search.");
                optionsList = rm.getAllBus();
                idx = Menu.getChoiceFromList(optionsList) - 1;
                listR = rm.getByBus(optionsList.get(idx));
                break;
            case 3:
                System.out.println("Enter a number correspond to the Brand you wanted to search.");
                optionsList = rm.getAllBrand();
                idx = Menu.getChoiceFromList(optionsList) - 1;
                listR = rm.getByBrand(optionsList.get(idx));
                break;
        }
        printLine(listR);
    }
    
    public void update() {
        System.out.println("[Update RAM]");
        
        String id = "RAM";
        
        List<String> options = rm.getAllType();
        
        System.out.println("Choose the type of RAM you want to update: ");
        int choice = Menu.getChoiceFromList(options) - 1;
        
        int Size = rm.getByType(options.get(choice)).size();
        id += options.get(choice);
        System.out.printf("The %s has %d item, enter a number to specify\n", 
                options.get(choice), Size);
        choice = Validation.checkInt("Input the specific RAM: ", Size);
        
        id += "_" + Integer.toString(choice - 1);
        options.clear();
        
        System.out.println("CODE: " + id);
        
        String type, bus, brand;
        int quantity;
        RAM item = new RAM(),
            original = rm.getByCode(id);
        
        System.out.println("Enter a number correspond to the Type you wanted to update.");
        
        options = rm.getAllType();
        options.add("Others.");
        choice = Menu.getChoiceFromList(options) - 1;
        type = options.get(choice);
        
        if (type.equals("Others.")) {
            type = Validation.checkString("Enter a new Type: ");
        }
        options.clear();
        
        System.out.println("Enter a number correspond to the Bus you wanted to update.");

        options = rm.getAllBus();
        options.add("Others.");
        choice = Menu.getChoiceFromList(options) - 1;
        bus = options.get(choice);
        
        if (bus.equals("Others.")) {
            bus = Validation.checkString("Enter a new Bus: ");
        }
        options.clear();
        
        System.out.println("Enter a number correspond to the Brand you wanted to update.");

        options = rm.getAllBrand();
        options.add("Others.");
        choice = Menu.getChoiceFromList(options) - 1;
        brand = options.get(choice);
        
        if (brand.equals("Others.")) {
            brand = Validation.checkString("Enter a new Brand: ");
        }
        options.clear();
        
        quantity = Validation.checkInt("Input the quantity: ");
        
        item = new RAM("RAM" + type + "_" + rm.getByType(type).size(), type, bus, brand, quantity, original.getProduction_month_year());

        
        if (!original.getActive()) {
            System.out.println("Item in-active\nDo you want to active it ? [YES] : [NO]");
            String ans = sc.nextLine().trim();
            if (ans.equalsIgnoreCase("YES")) 
                item.setActive(true);
        }
                
        if (rm.updateItem(id, item) != null) {
            System.out.println("Update success !!!");
        } else 
            System.out.println("Update failed !!!");
        
    }
    
    public void delete() {
        System.out.println("[Delete RAM]");
        
        String id = "RAM";
        
        List<String> options = rm.getAllType();
        
        System.out.println("Choose the type of RAM you want to update: ");
        int choice = Menu.getChoiceFromList(options) - 1;
        
        int Size = rm.getByType(options.get(choice)).size();
        id += options.get(choice);
        System.out.printf("The %s has %d item, enter a number to specify\n", 
                options.get(choice), Size);
        choice = Validation.checkInt("Input the specific RAM: ", Size);
        
        id += "_" + Integer.toString(choice - 1);
        options.clear();
        
        System.out.println("CODE: " + id);
        
        if (rm.deleteItem(id) != null) {
            System.out.println("Success !!!");
        } else {
            System.out.println("Failed !!!" + rm.deleteItem(id));
        }
    }
    
    public void save() {
        System.out.println("[Save]"); 
        try {
            rm.save();
            System.out.println("Saved !!!");
        } catch (Exception ex) {
            System.out.println("Save failed !!!");
        }
    }
    
    public void printAll() {
        System.out.println("[Print All RAM]");
        printLine(rm.showAll());
    }
    
    public void quit() {
        System.out.println("[Quit ?]");
        String ans = sc.nextLine().trim().toUpperCase();
        if (ans.equals("YES")) {
            save();
            System.out.println("Exiting");
            Platform.exit();
        } 
    }
    
    public String backToMenu() {
        System.out.println("Back to menu ? [Yes] : [No]");
        Scanner sc = new Scanner(System.in);
        String ans = sc.nextLine().trim().toUpperCase();
        if (ans.equals("NO")) {
            return ans.toUpperCase();
        } else {
            System.out.println("Returning to main menu");
        }
        return "YES";
    }
    
    public void test() {
        List<RAM> randomRAMList = new randomRAM().generateRandomRAMList(10);
        for (RAM ram : randomRAMList) {
            rm.addItem(ram);
        }
    }
}
