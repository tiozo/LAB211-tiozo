/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Viewer.Menu;
import fileIo.IFileReadWrite;
import fileIo.binWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import objectData.RAM;

/**
 *
 * @author tiozo
 */
public class ramManager implements iRAM<RAM> {

    private TreeMap<String, RAM> mapR = new TreeMap();
    
    public ramManager() throws Exception {
        this.load();
    }
    
    /// stand alone purpose is for referencing 
    final String ORDER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    class Compar implements Comparator<RAM> {
        @Override
        public int compare(RAM ram1, RAM ram2) {
            int typeComparison = ram1.getType().compareTo(ram2.getType());
            if (typeComparison != 0) {
                return typeComparison;
            }

            int busComparison = ram1.getBus().compareTo(ram2.getBus());
            if (busComparison != 0) {
                return busComparison;
            }

            return ram1.getBrand().compareTo(ram2.getBrand());
        }
    }
    
    /// RAM<type>_<numberic order> !!! NO NEED !!!
//    public void sort() {
//        int idx = 0;
//        for (RAM e: listR) {
//            /// we have the Type repeated 2 times; 
//            /// the time complexity would not be serverely.
//            String tmp = e.getType() + e.getBus() + e.getBrand() + e.getCode();
//            strTree.add(tmp, idx++);
//        }
//    }
    
    @Override
    public Boolean addItem(RAM item) {
        if (item == null || item.checkNull())
            return false;
        mapR.put(item.getCode(), item);
        return true;
    }

    @Override
    public RAM getByCode(String id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (id == null || id.isEmpty()) return null;
        RAM item = mapR.get(id); 
        return item;
    }

    @Override
    public List<RAM> getByBus(String id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<RAM> result = new ArrayList<>();
        for (RAM ram : mapR.values()) {
            if (ram.getBus().equals(id)) {
                result.add(ram);
            }
        }
        return result;
    }

    @Override
    public List<RAM> getByBrand(String id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<RAM> result = new ArrayList<>();
        for (RAM ram : mapR.values()) {
            if (ram.getBrand().equals(id)) {
                result.add(ram);
            }
        }
        return result;
    }

    @Override
    public List<RAM> getByType(String id) {
        ///throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        List<RAM> result = new ArrayList<>();
        for (RAM ram : mapR.values()) {
            if (ram.getType().equals(id)) {
                result.add(ram);
            }
        }
        return result;
    }

    @Override
    public RAM updateItem(String id, RAM item) {
        ///throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        RAM newRam = mapR.get(id);
        if (newRam != null) {
            if (!newRam.equals(item.getCode()))  
                mapR.remove(id);
            mapR.put(id, item);
            return newRam;
        } 
        return null;
    }

    @Override
    public RAM deleteItem(String id) {
        ///throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        RAM res = mapR.get(id);
        if (res != null) {
            res.setActive(false);
            res = updateItem(id, res);
        }
        return res;
    }

    @Override
    public List<RAM> showAll() {
        ///throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<RAM> res = new ArrayList<>(mapR.values());
        return res;
    }

    @Override
    public Boolean save() throws Exception {
        ///throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        IFileReadWrite typer = new binWriter();
        if (mapR == null) {
            return false;
        }
        try {
            return typer.write(mapR);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    private Boolean load() throws Exception {
        IFileReadWrite reader = new binWriter();
        try {
            mapR = reader.read();
            if (mapR != null)
                return true;
            else 
                return false;
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public List<String> getAllType() {
        List<String> res = new ArrayList();
        for (RAM e: mapR.values()) {
            res.add(e.type);
        }
        res = Menu.uniqueElements(res);
        return res;
    }
    
    public List<String> getAllBrand() {
        List<String> res = new ArrayList();
        for (RAM e: mapR.values()) {
            res.add(e.brand);
        }
        res = Menu.uniqueElements(res);
        return res;
    }
    
    public List<String> getAllBus() {
        List<String> res = new ArrayList();
        for (RAM e: mapR.values()) {
            res.add(e.bus);
        }
        res = Menu.uniqueElements(res);
        return res;
    }
    
    public int getSize() {
        return mapR.size();
    }
}
