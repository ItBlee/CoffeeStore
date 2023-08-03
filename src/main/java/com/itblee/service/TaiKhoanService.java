package com.itblee.service;

import com.itblee.dto.TaiKhoan;

import java.sql.Date;
import java.util.List;

public interface TaiKhoanService extends GenericService<TaiKhoan> {
    List<TaiKhoan> findByPhanQuyen(Integer maPQ);
    List<TaiKhoan> findByPhanQuyen(String TenPQ);
    List<TaiKhoan> findByTenDangNhap(String tenDangNhap);
    TaiKhoan findByNguoiSoHuu(Integer maNV);
    TaiKhoan findByNguoiSoHuu(String HoTenNV);
    List<TaiKhoan> findByNguoiTao(Integer MaNguoiTao);
    List<TaiKhoan> findByNguoiTao(String TenNguoiTao);
    List<TaiKhoan> findByNgayTao(Date tuNgay, Date denNgay);
    List<TaiKhoan> findByTinhTrang(Integer tinhTrang);

    boolean login(String username, String password);
}
