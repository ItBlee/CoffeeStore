package BUS.Interfaces;

import DTO.NhanVienDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public interface INhanVienBUS {
    ArrayList<NhanVienDTO> findAll();
    NhanVienDTO findByID(int id);
    NhanVienDTO findByMaTK(String maTK);
    ArrayList<NhanVienDTO> findByHoTen(String hoTen);
    ArrayList<NhanVienDTO> findByNgaySinh(Date ngaySinh);
    ArrayList<NhanVienDTO> findBySDT(String sdt);
    ArrayList<NhanVienDTO> findByEmail(String email);
    ArrayList<NhanVienDTO> findByGioiTinh(String gioiTinh);
    ArrayList<NhanVienDTO> findByLuong(Integer luong);

    void save(NhanVienDTO nhanVien) throws Exception;
    void update(NhanVienDTO nhanVien) throws Exception;
    void delete(int id) throws Exception;
    HashMap<Integer, Boolean> delete(int[] ids);

    boolean isExist(NhanVienDTO nhanVien);
    int getTotalCount();
}
