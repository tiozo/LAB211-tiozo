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
public class Brand extends Product {
    protected String id;
    public String name;
    public String country;
  
    public Brand() {
    }
    
    public Brand(String id) {
        this.id = id.toUpperCase();
    }
    
    public Brand(String id, String name, String country) {
        this.id = id.toUpperCase();
        this.name = name.toUpperCase();
        this.country = country.toUpperCase();
    }
    
    public Brand(Brand a) {
        this.id = a.id;
        this.name = a.name;
        this.country = a.country;
    }
    
    /// getter
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
    
    /// setter
    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    @Override 
    public String toString() {
        return String.format("%s, %s, %s", id, name, country);
    }
}
