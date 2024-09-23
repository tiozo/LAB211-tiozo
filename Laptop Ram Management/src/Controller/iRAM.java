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
public interface iRAM<E> {
    /// add item
    public Boolean addItem(E item);
    /// search function (id == code)
    public E getByCode(String id);
    public List<E> getByBus(String id);
    public List<E> getByBrand(String id);
    public List<E> getByType(String id);
    /// update function
    public E updateItem(String id, E item);
    /// delete = mark active = false;
    public E deleteItem(String id);
    /// printf
    public List<E> showAll();
    /// save to file
    public Boolean save() throws Exception;
}
