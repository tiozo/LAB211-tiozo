/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectData;

/**
 *
 * @author tiozo
 */
public class Product {
    protected String id;
    protected String brandId;
    protected String categoryId;
    public String name;
    public Integer modelYear;
    public Double listedPrice;

    public Product() {
    }

    public Product(String id, String name, Integer modelYear) {
        this.id = id.toUpperCase();
        this.name = name.toUpperCase();
        this.modelYear = modelYear;
    }
    
    public Product(String id) {
        this.id = id.toUpperCase();
    }
    
    public Product(String id, String name, String bid, String cid, Integer modelYear, Double listedPrice) {
        this.id = id.toUpperCase();
        this.name = name.toUpperCase();
        this.brandId = bid.toUpperCase();
        this.categoryId = cid.toUpperCase();
        this.modelYear = modelYear;
        this.listedPrice = listedPrice;
    }
    
    public Product(Product p) {
        if (p == null) {
            return;
        }
        this.id = p.id;
        this.name = p.name;
        this.brandId = p.brandId;
        this.categoryId = p.categoryId;
        this.modelYear = p.modelYear;
        this.listedPrice = p.listedPrice;
    }
    
    /// getter
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrandIdx() {
        return brandId;
    }

    public String getCategoryIdx() {
        return categoryId;
    }
    
    public int getModelYear() {
        return modelYear;
    }

    public Double getListedPrice() {
        return listedPrice;
    }

    /// setter
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrandIdx(String brandIdx) {
        this.brandId = brandIdx;
    }

    public void setCategoryIdx(String categoryIdx) {
        this.categoryId = categoryIdx;
    }
    
    public void setModel(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public void setListedPrice(Double listedPrice) {
        this.listedPrice = listedPrice;
    }
    
    @Override
    public String toString() {
        return String.format("%s, %s, %d, %s", id, name, modelYear, listedPrice);
    }
    
    public Product copy() {
        return new Product(this);
    }
}
