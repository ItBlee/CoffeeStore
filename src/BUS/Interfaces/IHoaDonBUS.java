package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.HoaDonDTO;

import java.sql.Date;
import java.util.ArrayList;

public interface IHoaDonBUS extends ISearchableBUS<HoaDonDTO>, ICrudBUS<HoaDonDTO> {
    ArrayList<HoaDonDTO> findByKhachHang(Integer MaKH);
    ArrayList<HoaDonDTO> findByKhachHang(String TenKH);
    ArrayList<HoaDonDTO> findByNhanVien(Integer MaNV);
    ArrayList<HoaDonDTO> findByNhanVien(String TenNV);
    ArrayList<HoaDonDTO> findByNgayLap(Date tuNgay, Date denNgay);
    ArrayList<HoaDonDTO> findByTongTien(Integer tien);
    ArrayList<HoaDonDTO> findByTienKhuyenMai(Integer tien);
    ArrayList<HoaDonDTO> findByTinhTrang(Integer TinhTrang);
}
