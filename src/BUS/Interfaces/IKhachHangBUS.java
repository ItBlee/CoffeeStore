package BUS.Interfaces;

import DTO.KhachHangDTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface IKhachHangBUS {
    ArrayList<KhachHangDTO> findAll();
    KhachHangDTO findByID(int id);
    KhachHangDTO findByMaTK(Integer maTK);
    ArrayList<KhachHangDTO> findByHoTen(String hoTen);
    ArrayList<KhachHangDTO> findBySDT(String sdt);
    ArrayList<KhachHangDTO> findByDiaChi(String diaChi);
    ArrayList<KhachHangDTO> findBySoTaiKhoan(String soTaiKhoan);
    ArrayList<KhachHangDTO> findByTinhTrang(String tinhTrang);

    void save(KhachHangDTO nhanVien) throws Exception;
    void update(KhachHangDTO nhanVien) throws Exception;
    void delete(int id) throws Exception;
    HashMap<Integer, Boolean> delete(int[] ids);

    boolean isExist(KhachHangDTO nhanVien);
    int getTotalCount();
}
