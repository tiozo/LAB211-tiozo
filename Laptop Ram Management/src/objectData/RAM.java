/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectData;

import java.io.Serializable;

/**
 *
 * @author tiozo
 */
public class RAM implements Comparable<RAM>, Serializable {
    protected  String code;
    public String type;
    public String bus;
    public String brand;
    public int quantity;
    public String production_month_year;
    protected Boolean active;

    private static final long serialVersionUID = 1L;
    
    public RAM() {
    }
    
    public RAM(String type, String bus, String brand) {
        this.type = type;
        this.bus = bus;
        this.brand = brand;
    }
    
    public RAM(String code, String type, String bus, String brand, int quantity, String production_month_year) {
        this.code = code;
        this.type = type;
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.production_month_year = production_month_year;
        this.active = true;
    }
    
    public RAM(RAM item) {
        if (item == null) return;
        this.code = item.code;
        this.type = item.type;
        this.brand = item.brand;
        this.bus = item.bus;
        this.quantity = item.quantity;
        this.production_month_year = item.production_month_year;
        this.active = item.active;
    }
    
    public int compareTo(RAM ram2) {
        int typeComparison = this.getType().compareTo(ram2.getType());
        if (typeComparison != 0) {
            return typeComparison;
        }

        int busComparison = this.getBus().compareTo(ram2.getBus());
        if (busComparison != 0) {
            return busComparison;
        }

        return this.getBrand().compareTo(ram2.getBrand());
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduction_month_year() {
        return production_month_year;
    }

    public void setProduction_month_year(String production_month_year) {
        this.production_month_year = production_month_year;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    public RAM copy(RAM item) {
        return new RAM(item);
    } 
    
    public Boolean checkNull() {
        if (this.type == null || this.bus == null || this.brand == null || this.quantity == 0 || this.production_month_year == null)
            return true;
        return false;
    }
    
    public String toString() {
        String s = String.format("%14s | %10s | %10s | %10s | %5d | %14s", this.code, this.type, this.bus, this.brand, this.quantity, this.production_month_year);
        return s;
    }
}
