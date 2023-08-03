package com.itblee.service;

import com.itblee.dto.SanPham;

import java.util.List;

public interface SanPhamService extends GenericService<SanPham> {
    List<SanPham> findByNhaCungCap(Integer MaNCC);
    List<SanPham> findByNhaCungCap(String TenNCC);
    List<SanPham> findByLoaiSP(Integer MaLoai);
    List<SanPham> findByLoaiSP(String TenLoai);
    List<SanPham> findByTenSP(String tenSP);
    List<SanPham> findByDonGia(Integer tien);
    List<SanPham> findByDonVi(String donVi);
    List<SanPham> findBySoLuong(Integer soluong);
    List<SanPham> findByTinhTrang(Integer tinhTrang);
}
