package com.itblee.service;

import com.itblee.dto.PhieuNhap;

import java.sql.Date;
import java.util.List;

public interface PhieuNhapService extends GenericService<PhieuNhap> {
    List<PhieuNhap> findByNCC(Integer maNCC);
    List<PhieuNhap> findByNCC(String TenNCC);
    List<PhieuNhap> findByNhanVien(Integer maNV);
    List<PhieuNhap> findByNhanVien(String TenNV);
    List<PhieuNhap> findByNgayTao(Date tuNgay, Date denNgay);
    List<PhieuNhap> findByTongTien(Integer tien);
    List<PhieuNhap> findByTinhTrang(Integer tinhTrang);
}
