package Services.Interfaces;

import DTO.TaiKhoanDTO;

import java.util.HashMap;
import java.util.List;

public interface ITaiKhoanService {
    List<TaiKhoanDTO> findAll();
    TaiKhoanDTO findByID(int id);
    TaiKhoanDTO save(TaiKhoanDTO taikhoan);
    TaiKhoanDTO update(TaiKhoanDTO taikhoan);
    HashMap<Integer, Boolean> delete(int[] ids);
    boolean login(String username, String password);
    int getTotalCount();
}
