package BUS.Interfaces;

import DTO.TaiKhoanDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public interface ITaiKhoanBUS {
    ArrayList<TaiKhoanDTO> findAll();
    TaiKhoanDTO findByID(int id);
    TaiKhoanDTO findByTenDangNhap(String tenDangNhap);
    ArrayList<TaiKhoanDTO> findByNgayTao(Date ngayTao);
    ArrayList<TaiKhoanDTO> findByNguoiTao(Integer nguoiTao);
    ArrayList<TaiKhoanDTO> findByChucVu(String chucVu);
    ArrayList<TaiKhoanDTO> findByTinhTrang(Integer tinhTrang);


    void save(TaiKhoanDTO taikhoan) throws Exception;
    void update(TaiKhoanDTO taikhoan) throws Exception;
    void delete(int id) throws Exception;
    HashMap<Integer, Boolean> delete(int[] ids);

    boolean isExist(TaiKhoanDTO taikhoan);
    int getTotalCount();
    boolean login(String username, String password);
    void logout();
}
