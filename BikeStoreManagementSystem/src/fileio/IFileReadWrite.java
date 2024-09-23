package fileio;

import java.util.List;

public interface IFileReadWrite<E, T, F> {
    
    List<E> readP()throws Exception;
    List<T> readB()throws Exception;
    List<F> readC()throws Exception;
    boolean write(List<E> list)throws Exception;
        
}
