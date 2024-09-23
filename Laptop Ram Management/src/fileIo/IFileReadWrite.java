package fileIo;

import java.util.TreeMap;

public interface IFileReadWrite<E> {
    
    TreeMap<String, E> read()throws Exception;
    boolean write(TreeMap<String, E> ramMap)throws Exception;
        
}
