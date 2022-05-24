package BUS.Interfaces;

import BUS.Interfaces.common.ISearchableBUS;
import BUS.Interfaces.common.ICrudBUS;
import DTO.TaiKhoanDTO;

import java.sql.Date;
import java.util.ArrayList;

public interface ITaiKhoanBUS extends ISearchableBUS<TaiKhoanDTO>, ICrudBUS<TaiKhoanDTO> {
    ArrayList<TaiKhoanDTO> findByMaPQ(Integer maPQ);
    ArrayList<TaiKhoanDTO> findByTenDangNhap(String tenDangNhap);
    ArrayList<TaiKhoanDTO> findByNgayTao(Date tuNgay, Date denNgay);
    ArrayList<TaiKhoanDTO> findByNguoiTao(Integer nguoiTao);
    ArrayList<TaiKhoanDTO> findByTinhTrang(Integer tinhTrang);

    boolean login(String username, String password);
    void logout();
}
