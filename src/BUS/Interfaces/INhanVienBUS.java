package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.NhanVienDTO;

import java.sql.Date;
import java.util.ArrayList;

public interface INhanVienBUS extends ISearchableBUS<NhanVienDTO>, ICrudBUS<NhanVienDTO> {
    NhanVienDTO findByMaTK(Integer maTK);
    ArrayList<NhanVienDTO> findByHoTen(String hoTen);
    ArrayList<NhanVienDTO> findByNgaySinh(Date ngaySinh);
    ArrayList<NhanVienDTO> findBySDT(Integer sdt);
    ArrayList<NhanVienDTO> findByEmail(String email);
    ArrayList<NhanVienDTO> findByGioiTinh(Integer gioiTinh);
    ArrayList<NhanVienDTO> findByLuong(Integer luong);
}
