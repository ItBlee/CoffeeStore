package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.NhanVienDTO;

import java.sql.Date;
import java.util.ArrayList;

public interface INhanVienBUS extends ISearchableBUS<NhanVienDTO>, ICrudBUS<NhanVienDTO> {
    NhanVienDTO findByTaiKhoan(Integer maTK);
    NhanVienDTO findByTaiKhoan(String tenTK);
    ArrayList<NhanVienDTO> findByHoTen(String hoTen);
    ArrayList<NhanVienDTO> findByNgaySinh(Date tuNgay, Date denNgay);
    ArrayList<NhanVienDTO> findBySDT(String sdt);
    ArrayList<NhanVienDTO> findByEmail(String email);
    ArrayList<NhanVienDTO> findByGioiTinh(Integer gioiTinh);
    ArrayList<NhanVienDTO> findByLuong(Integer luong);
    ArrayList<NhanVienDTO> findByTinhTrang(Integer TinhTrang);

}
