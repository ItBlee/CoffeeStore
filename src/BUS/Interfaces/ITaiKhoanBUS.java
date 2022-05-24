package BUS.Interfaces;

import BUS.Interfaces.common.ISearchableBUS;
import BUS.Interfaces.common.ICrudBUS;
import DTO.TaiKhoanDTO;

import java.sql.Date;
import java.util.ArrayList;

public interface ITaiKhoanBUS extends ISearchableBUS<TaiKhoanDTO>, ICrudBUS<TaiKhoanDTO> {
    ArrayList<TaiKhoanDTO> findByPhanQuyen(Integer maPQ);
    ArrayList<TaiKhoanDTO> findByPhanQuyen(String TenPQ);
    ArrayList<TaiKhoanDTO> findByTenDangNhap(String tenDangNhap);
    TaiKhoanDTO findByNguoiSoHuu(Integer maNV);
    TaiKhoanDTO findByNguoiSoHuu(String HoTenNV);
    ArrayList<TaiKhoanDTO> findByNguoiTao(Integer MaNguoiTao);
    ArrayList<TaiKhoanDTO> findByNguoiTao(String TenNguoiTao);
    ArrayList<TaiKhoanDTO> findByNgayTao(Date tuNgay, Date denNgay);
    ArrayList<TaiKhoanDTO> findByTinhTrang(Integer tinhTrang);

    boolean login(String username, String password);
}
