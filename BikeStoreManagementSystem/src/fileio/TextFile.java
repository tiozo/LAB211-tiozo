/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileio;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import ObjectData.*;
import Controller.*;

/**
 *
 * @author Admin
 */
public class TextFile implements IFileReadWrite<Product, Brand, Category> {
    private final String fileName = "src/FileLoc/Product List";
    private final String extension = ".txt";
    
    private final String brandFileName = "src/FileLoc/01_Brand";
    private final String cateFileName = "src/FileLoc/01_Category";
    
    public List<Product> readP() throws Exception {
        List<Product> res = new ArrayList<Product>();
        
        try {
            File file = new File(fileName + extension);
            if (!file.exists()) {
                return null;
            }
            BufferedReader rd = null;
            FileInputStream  input = new FileInputStream(fileName + extension);
            rd = new BufferedReader(new InputStreamReader(input));
            String line;
            Product pro = null;
            
            while ((line = rd.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] splited = line.split(", ");
                String ID = new String(), 
                       name = new String(), 
                       brandId = new String(), 
                       categoryId = new String();
                double listedPrice;
                int modelYear, bid, cid;
                ID = splited[0].trim();
                name = splited[1].trim();
                brandId = splited[2].trim();
                categoryId = splited[3].trim();
                modelYear = Integer.parseInt(splited[4].trim());
                listedPrice = Double.parseDouble(splited[5].trim());
                
                pro = new Product(ID, name, brandId, categoryId, modelYear, listedPrice);
                        
                res.add(pro);
            }
            input.close();
        } catch (Exception e) {
            throw e;
        } 
        return res.isEmpty() ? null : res;
    }
    
    public List<Brand> readB() throws Exception {
        List<Brand> res = new ArrayList<Brand>();
        
        try {
            File file = new File(brandFileName + extension);
            if (!file.exists()) {
                return null;
            }
            BufferedReader rd = null;
            FileInputStream  input = new FileInputStream(brandFileName + extension);
            rd = new BufferedReader(new InputStreamReader(input));
            String line;
            Brand brand = null;
            
            while ((line = rd.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] splited = line.split(", ");
                String ID = new String(), 
                       name = new String(), 
                       country = new String();
                ID = splited[0].trim();
                name = splited[1].trim();
                country = splited[2].trim();
                
                brand = new Brand(ID, name, country);
                        
                res.add(brand);
            }
            input.close();
        } catch (Exception e) {
            throw e;
        } 
        return res.isEmpty() ? null : res;
    }
    
    public List<Category> readC() throws Exception {
        List<Category> res = new ArrayList<Category>();
        
        try {
            File file = new File(cateFileName + extension);
            if (!file.exists()) {
                return null;
            }
            BufferedReader rd = null;
            FileInputStream  input = new FileInputStream(cateFileName + extension);
            rd = new BufferedReader(new InputStreamReader(input));
            String line;
            Category cate = null;
            
            while ((line = rd.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] splited = line.split(", ");
                String ID = new String(), 
                       type = new String();
                ID = splited[0].trim();
                type = splited[1].trim();
                
                cate = new Category(ID, type);
                        
                res.add(cate);
            }
            input.close();
        } catch (Exception e) {
            throw e;
        } 
        return res.isEmpty() ? null : res;
    }
    
    public boolean write(List<Product> list) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
        try {
            PrintWriter file = new PrintWriter(fileName + extension);
            for (Product e: list) {
                //// add two new way to get the Brand ID, Category ID or the Name of these field
                file.printf("%s, %s, %s, %s, %d, %.2f\n", e.getId(), e.getName(), e.getBrandIdx(), e.getCategoryIdx() , e.getModelYear(), e.getListedPrice());
            }
            file.close();
        } catch (Exception e) {
            throw e;
        } 
        return true;
    }
}
