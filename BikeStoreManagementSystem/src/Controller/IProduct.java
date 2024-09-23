/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.List;

/**
 *
 * @author tiozo
 */
public interface IProduct<E> {

    /*
    getting the Item ID
     */
    public E getId(String id);
    /*
    add the Item
    */
    public Boolean addItem(E item);
    /*
    update the item
    */
    public Boolean updateItem(E item);
    /*
    delete the item
    */
    public Boolean deleteItem(E item);
    /*
    save the current state of the data
    */
    public Boolean save() throws Exception;
    /*
    load the current state of the data
    */
    public Boolean load() throws Exception;
    /*
    getting all the item stored within.
    */
    public List<E> getAll();
}
