/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ObjectData.*;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import fileio.*;
import java.util.ArrayList;

/**
 *
 * @author tiozo
 */

public class ProductManagement implements IProduct<Product> {
    /// P = "Product", B = "Brand", C = "Category"
    /// assign = null
    public List<Product> listP = null;
    public List<Brand> listB = null;
    public List<Category> listC = null;
    
    public ProductManagement() throws Exception {
        this.load();
    }
    
    final String ORDER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    class productCompar implements Comparator<Product> {
        @Override
        public int compare(Product s1, Product s2) {
            if (s1.getId() == null ? s2.getId() == null : s1.getId().equals(s2.getId())) {
                return 0;
            }
            int p1 = 0, p2 = 0;
            String o1 = s1.getId(), o2 = s2.getId();
            for (int i = 0; i < Math.min(o1.length(), o2.length()) && p1 == p2; ++i) {
                p1 = ORDER.indexOf(o1.charAt(i));
                p2 = ORDER.indexOf(o2.charAt(i));
            }
            if (p1 == p2 && o1.length() != o2.length()) {
                return o1.length() - o2.length();
            }
            return p1 - p2;
        }
    }
    
    class brandCompar implements Comparator<Brand> {
        @Override
        public int compare(Brand s1, Brand s2) {
            if (s1.getId() == null ? s2.getId() == null : s1.getId().equals(s2.getId())) {
                return 0;
            }
            int p1 = 0, p2 = 0;
            String o1 = s1.getId(), o2 = s2.getId();
            for (int i = 0; i < Math.min(o1.length(), o2.length()) && p1 == p2; ++i) {
                p1 = ORDER.indexOf(o1.charAt(i));
                p2 = ORDER.indexOf(o2.charAt(i));
            }
            if (p1 == p2 && o1.length() != o2.length()) {
                return o1.length() - o2.length();
            }
            return p1 - p2;
        }
    }
        
    class cateCompar implements Comparator<Category> {
        @Override
        public int compare(Category s1, Category s2) {
            if (s1.getId() == null ? s2.getId() == null : s1.getId().equals(s2.getId())) {
                return 0;
            }
            int p1 = 0, p2 = 0;
            String o1 = s1.getId(), o2 = s2.getId();
            for (int i = 0; i < Math.min(o1.length(), o2.length()) && p1 == p2; ++i) {
                p1 = ORDER.indexOf(o1.charAt(i));
                p2 = ORDER.indexOf(o2.charAt(i));
            }
            if (p1 == p2 && o1.length() != o2.length()) {
                return o1.length() - o2.length();
            }
            return p1 - p2;
        }
    }    
    
    class PriceNameDes implements Comparator<Product> {
        @Override
        public int compare(Product s1, Product s2) {
            if (s1.getListedPrice() == s2.getListedPrice() && s1.getName().equals(s2.getName())) {
                return 0;
            }
            double p1 = s1.getListedPrice(), p2 = s2.getListedPrice();
            double exp = 0.0000000001;
            if (Math.abs(p1 - p2) <= exp) {
                return s1.getName().compareTo(s2.getName());
            } 
            return (int)(p1) - (int)(p2);
        }
    }
    
    /// sort by Name
    public void init() {
        Collections.sort(listP, new productCompar());
    } 
    
    public void initB() {
        Collections.sort(listB, new brandCompar());
    }
    
    public void initC() {
        Collections.sort(listC, new cateCompar());
    }
    
    /// sort by price, name desecending
    public void sortByPriceNameDes(List<Product> input) {
        Collections.sort(input, new PriceNameDes());
    }
    
    @Override
    /*
    Set of get ID for Product, Brand, Category with correspond .listP, .listB, .listC
    */
    public Product getId(String pid) {
        Product result = null;
        pid = pid.toUpperCase();
        int id;
        if (listP != null && !listP.isEmpty()) {
            id = Collections.binarySearch(listP, new Product(pid), new productCompar());
            if (id >= 0 && id <= listP.size())
                result = listP.get(id);
        }
        return result;
    }
    
    public Brand getBrand(String bid) {
        Brand res = null;
        bid = bid.toUpperCase();
        if (listB != null && !listB.isEmpty()) {
            int id = Collections.binarySearch(listB, new Brand(bid), new brandCompar());
            if (id >= 0 && id <= listB.size())
                res = listB.get(id);
        }
        return res;
    }

    public Category getCate(String cid) {
        Category res = null;
        cid = cid.toUpperCase();
        if (listC != null && !listC.isEmpty()) {
            int id = Collections.binarySearch(listC, new Category(cid), new cateCompar());
            if (id >= 0 && id <= listC.size())
                res = listC.get(id);
        }
        return res;
    }
    
    @Override
    public Boolean addItem(Product item) {
        if (item != null) {
            listP.add(item);
            init();
            return true;
        }
        return false;
    }
    
    public Boolean addBrand(Brand item) {
        if (item != null) {
            listB.add(item);
            initB();
            return true;
        }
        return false;
    }
    
    public Boolean addCate(Category item) {
        if (item != null) {
            listC.add(item);
            initC();
            return true;
        }
        return false;
    }
    
    @Override
    /*
    Using the item.getId(); 
    So before searching need to custom and pack it before modifying
    */
    public Boolean updateItem(Product item) {
        if (item != null) {
            int id = Collections.binarySearch(listP, new Product(item.getId()), new productCompar());
            listP.set(id, item);
            return true;
        }
        return false;
    }
    
    public Boolean updateBrand(Brand item) {
        if (item != null) {
            int id = Collections.binarySearch(listB, new Brand(item.getId()), new brandCompar());
            listB.set(id, item);
            return true;
        }
        return false;
    }
    
    public Boolean updateCate(Category item) {
        if (item != null) {
            int id = Collections.binarySearch(listC, new Category(item.getId()), new cateCompar());
            listC.set(id, item);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteItem(Product item) {
        if (item != null) {
            listP.remove(Collections.binarySearch(listP, new Product(item.getId()), new productCompar()));
            init();
            return true;
        }
        return false;
    }
    
    public Boolean deleteBrand(Brand item) {
        if (item != null) {
            listB.remove(Collections.binarySearch(listB, new Brand(item.getId()), new brandCompar()));
            initB();
            return true;
        }
        return false;
    }
    
    public Boolean deleteCate(Category item) {
        if (item != null) {
            listC.remove(Collections.binarySearch(listC, new Category(item.getId()), new cateCompar()));
            initC();
            return true;
        }
        return false;
    }

    @Override
    public Boolean save() throws Exception {
        IFileReadWrite typer = new TextFile();
        if (listP == null) {
            return false;
        }
        init(); initB(); initC();
        try {
            return typer.write(listP);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public Boolean load() throws Exception {
        IFileReadWrite loader = new TextFile();
        try {
            listP = loader.readP();
            listB = loader.readB();
            listC = loader.readC();
            init(); initB(); initC();
        } catch (Exception ex) {
            throw ex;
        }
        return (((!listB.isEmpty() && listB != null) &&
                (!listC.isEmpty() && listC != null)));
    }

    @Override
    public List<Product> getAll() {
        return this.listP;
    }    
    
    public Boolean isExist(String id) {
        return (this.getId(id) != null);
    }
    
    public Boolean isExistBrandID(String id) {
        return (this.getBrand(id) != null);
    }
    
    public Boolean isExistCategoryID(String id) {
        return (this.getCate(id) != null);
    }
    
    /*
    Linking the Brand and the category, 
    !!!! must be add after the read function in main. !!!!
    Never mind 
    this function is old idea
    */
    public void printAll() {
        int idx = 1;
        List<Product> dummy = new ArrayList<>();
        
        for (Product e: listP) {
            dummy.add(e.copy());
        }
        
        sortByPriceNameDes(dummy);
        if (!dummy.isEmpty() && null != dummy) {
            for (Product e: dummy) {
                System.out.printf("%d. %s | %s | %s | %s | %d | %.2f\n",idx++ ,e.getId() ,e.getName() , (getBrand(e.getBrandIdx())).getName(), 
                                  (getCate(e.getCategoryIdx())).getType(), e.getModelYear(), e.getListedPrice());
            }
        }
        else {
            System.out.println("List Empty !!!");
        }
    }
    
    /*
    Naive solution
    Put the String into Array of Characters.
    check if s1 contains any of the current characters
    for (int i = 0; i < s.length; ++i) {
        if s.charAt(i) == current in index of String 2 
            => add make the flag true;
    Side note: this algo could have 1 more attribute. Makes a score system for how good a matching is.
    }
    */
    public static boolean partialMatching(String s1, String s2) {
        boolean flag = false;
        int n = s1.length();
        int j = 0;
        for (int i = 0; i < n; ++i) {
            if (s1.charAt(i) == s2.charAt(j)) {
                ++j;
            }
            if (j == s2.length()) {
                flag = true; break;
            }
        }  
        return flag;
    }
    
    public List<Product> searchProduct(String s) {
        List<Product> res = new ArrayList<Product>();
        s = s.toUpperCase();
        for (Product e: listP) {
            if (partialMatching(e.getName(), s)) {
                res.add(e);
            }
        }
        /// to reverse it just add .reversed();
        if (res != null && !res.isEmpty())
            res.sort(Comparator.comparing(Product::getModelYear));
        else 
            return Collections.EMPTY_LIST;
        return res;
    }
}
