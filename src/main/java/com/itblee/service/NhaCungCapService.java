package com.itblee.service;

import com.itblee.dto.NhaCungCap;

import java.util.List;

public interface NhaCungCapService extends GenericService<NhaCungCap> {
    List<NhaCungCap> findByTenNCC(String tenNCC);
    List<NhaCungCap> findBySDT(String sdt);
    List<NhaCungCap> findByDiaChi(String diaChi);
    List<NhaCungCap> findBySoTaiKhoan(String stk);
    List<NhaCungCap> findByTinhTrang(Integer tinhTrang);
}
