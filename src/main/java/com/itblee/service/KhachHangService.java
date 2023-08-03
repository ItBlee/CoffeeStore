package com.itblee.service;

import com.itblee.dto.KhachHang;

import java.util.List;

public interface KhachHangService extends GenericService<KhachHang> {
    List<KhachHang> findByHoTen(String hoTen);
    List<KhachHang> findBySDT(String sdt);
    List<KhachHang> findByDiaChi(String diaChi);
    List<KhachHang> findByEmail(String mail);
    List<KhachHang> findByTinhTrang(Integer tinhTrang);
}
