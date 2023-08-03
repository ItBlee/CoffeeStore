package com.itblee.dao.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dao.SanPhamDAO;
import com.itblee.dto.SanPham;
import com.itblee.mapper.SanPhamMapper;

import java.util.List;

@AutoService(SanPhamDAO.class)
public class SanPhamDAOImpl extends AbstractDAO<SanPham> implements SanPhamDAO {

    private final SanPhamMapper mapper = Provider.get(SanPhamMapper.class);

    @Override
    public List<SanPham> findAll() {
        String sql = "SELECT * FROM sanpham";
        return query(sql, mapper);
    }

    @Override
    public SanPham findByID(int MaSP) {
        String sql = "SELECT * FROM sanpham WHERE MaSP = ?";
        List<SanPham> result = query(sql, mapper, MaSP);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(SanPham sanPham) {
        String sql = "INSERT INTO sanpham"
                + " (MaSP, MaLoai, MaNCC, TenSP, MoTa, HinhAnh, DonGia, DonVi, SoLuong, TinhTrang)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, sanPham.getMaSP(), sanPham.getMaLoai(), sanPham.getMaNCC(), sanPham.getTenSP(),
                sanPham.getMoTa(), sanPham.getHinhAnh(), sanPham.getDonGia(), sanPham.getDonVi(),
                sanPham.getSoLuong(), sanPham.getTinhTrang());
    }

    @Override
    public boolean update(SanPham sanPham) {
        String sql = "UPDATE sanpham"
                + " SET MaLoai = ?, MaNCC = ?, TenSP = ?, MoTa = ?, HinhAnh = ?, DonGia = ?, DonVi = ?, SoLuong = ?, TinhTrang = ?"
                + " WHERE MaSP = ?";
        return update(sql, sanPham.getMaLoai(), sanPham.getMaNCC(), sanPham.getTenSP(), sanPham.getMoTa(),
                sanPham.getHinhAnh(), sanPham.getDonGia(), sanPham.getDonVi(), sanPham.getSoLuong(),
                sanPham.getTinhTrang(), sanPham.getMaSP());
    }

    @Override
    public boolean delete(int MaSP) {
        String sql = "DELETE FROM sanpham"
                + " WHERE MaSP = ?";
        return update(sql, MaSP);
    }
}
