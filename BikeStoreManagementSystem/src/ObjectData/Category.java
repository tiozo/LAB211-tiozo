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
public class Category extends Product {
    protected String id;
    public String type;

    public Category() {
    }

    public Category(String id) {
        this.id = id.toUpperCase();
    }
    
    public Category(String id, String type) {
        this.id = id.toUpperCase();
        this.type = type.toUpperCase();
    }
    
    public Category(Category c) {
        this.id = c.id;
        this.type = c.type;
    }
    
    /// getter
    @Override
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    /// setter
    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    public String toString() {
        return String.format("%s, %s", id, type);
    }
}
