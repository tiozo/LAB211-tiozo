/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import objectData.RAM;

/**
 *
 * @author tiozo
 */
public class randomRAM {
    public List<RAM> generateRandomRAMList(int numRAMs) {
        List<RAM> ramList = new ArrayList<>();
        Random random = new Random();

        String[] types = {"DDR3", "DDR4", "DDR5", "LPDDR3", "LPDDR4", "LPDDR5", "GDDR5", "GDDR6", "GDDR6X"};
        String[] brands = {"Samsung", "Micron", "SK Hynix", "Crucial", "Corsair", "Kingston", "Team Group", "Adata", "Patriot", "G.Skill", "EVGA"};
        String[] buses = {"DDR3-1600", "DDR3-1866", "DDR4-2133", "DDR4-2400", "DDR4-2666", "DDR4-3000", "DDR4-3200", "DDR4-3600", "DDR5-4800", "DDR5-5200", "DDR5-6000", "LPDDR3-1600", "LPDDR4-2133", "LPDDR4-3200", "LPDDR5-4267", "LPDDR5-5500"};

        int[] size = new int[9];
        
        for (int i = 0; i < numRAMs; i++) {
            int typeIdx = random.nextInt(types.length);
            String type = types[typeIdx];
            String code = "RAM" + type + "_" + size[typeIdx]++; // Implement this method to generate unique codes       
            String brand = brands[random.nextInt(brands.length)];
            String bus = buses[random.nextInt(buses.length)];
            int quantity = random.nextInt(16) + 1; // Assuming a maximum of 16 modules
            String productionMonthYear = generateRandomMonthYear(); // Implement this method to generate random month-year

            RAM ram = new RAM(code, type, bus, brand, quantity, productionMonthYear);
            ramList.add(ram);
        }

        return ramList;
    }

    // Implement these methods as needed
    private String generateRandomCode() {
        // Your code to generate unique random codes
        return "RAM-" + Math.random(); // Example: Replace with a more robust method
    }

    private String generateRandomMonthYear() {
        // Your code to generate random month-year in the desired format
        return "2023-" + (int) (Math.random() * 12 + 1); // Example: Replace with a more robust method
    }
}
