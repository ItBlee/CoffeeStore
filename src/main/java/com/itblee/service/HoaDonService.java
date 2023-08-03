package com.itblee.service;

import com.itblee.dto.HoaDon;

import java.sql.Date;
import java.util.List;

public interface HoaDonService extends GenericService<HoaDon> {
    List<HoaDon> findByKhachHang(Integer MaKH);
    List<HoaDon> findByKhachHang(String TenKH);
    List<HoaDon> findByNhanVien(Integer MaNV);
    List<HoaDon> findByNhanVien(String TenNV);
    List<HoaDon> findByNgayLap(Date tuNgay, Date denNgay);
    List<HoaDon> findByTongTien(Integer tien);
    List<HoaDon> findByTienKhuyenMai(Integer tien);
    List<HoaDon> findByTinhTrang(Integer TinhTrang);
}
