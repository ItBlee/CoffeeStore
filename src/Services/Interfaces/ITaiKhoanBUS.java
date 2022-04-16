package Services.Interfaces;

import DTO.TaiKhoanDTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface ITaiKhoanBUS {
    ArrayList<TaiKhoanDTO> findAll();
    TaiKhoanDTO findByID(int id);

    boolean save(TaiKhoanDTO taikhoan);
    boolean update(TaiKhoanDTO taikhoan);
    boolean delete(int id);
    HashMap<Integer, Boolean> delete(int[] ids);

    int getTotalCount();
    boolean login(String username, String password);
    void logout();
}
