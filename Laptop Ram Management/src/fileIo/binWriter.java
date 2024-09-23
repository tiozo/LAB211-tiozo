/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileIo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;
import objectData.RAM;

/**
 *
 * @author tiozo
 */
public class binWriter implements IFileReadWrite<RAM> {
    
    private final String fileName = "src/fileIo/RAMModules.dat";

    @Override
    public TreeMap<String, RAM> read() throws Exception {
        TreeMap<String, RAM> ramMap = new TreeMap<>();
        File file = null;
        FileInputStream fileIn = null;
        ObjectInputStream input = null;

        try {
            file = new File(fileName);
            
            if (file.length() == 0) {
                System.out.println("File is empty");
                return ramMap;
            }
            
            fileIn = new FileInputStream(file);
            input = new ObjectInputStream(fileIn);
                        
            Object obj = input.readObject();
                        ///System.out.println("HEREEEE");

            if (obj != null && obj instanceof TreeMap) {
                ramMap = (TreeMap<String, RAM>) obj;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (input != null) {
                input.close();
            }
            if (fileIn != null) {
                fileIn.close();
            }
        }
        return ramMap;
    }

    @Override
    public boolean write(TreeMap<String, RAM> ramMap) throws Exception {
        File file = null;
        FileOutputStream fileOut = null;
        ObjectOutputStream output = null;

        try {
            file = new File(fileName);
            fileOut = new FileOutputStream(file);
            output = new ObjectOutputStream(fileOut);
            output.writeObject(ramMap);
        } catch (Exception e) {
            throw e;
        } finally {
            if (output != null) {
                output.close();
            }
            if (fileOut != null) {
                fileOut.close();
            }
        }

        return true;
    }
    
}
