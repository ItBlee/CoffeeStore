package BUS.Interfaces.common;

import java.util.HashMap;

public interface ICrudBUS<T> {
    Integer save(T entity) throws Exception;
    void update(T entity) throws Exception;
    void delete(int id) throws Exception;
    HashMap<Integer, Boolean> delete(int[] ids);
}
