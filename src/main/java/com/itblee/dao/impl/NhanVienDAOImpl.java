package com.itblee.dao.impl;

import com.google.auto.service.AutoService;
import com.itblee.dao.NhanVienDAO;
import com.itblee.dto.NhanVien;
import com.itblee.mapper.NhanVienMapper;
import com.itblee.Provider;

import java.util.List;

@AutoService(NhanVienDAO.class)
public class NhanVienDAOImpl extends AbstractDAO<NhanVien> implements NhanVienDAO {

    private final NhanVienMapper mapper = Provider.get(NhanVienMapper.class);

    @Override
    public List<NhanVien> findAll() {
        String sql = "SELECT * FROM nhanvien";
        return query(sql, mapper);
    }

    @Override
    public NhanVien findByID(int MaNV) {
        String sql = "SELECT * FROM nhanvien WHERE MaNV = ?";
        List<NhanVien> result = query(sql, mapper, MaNV);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Integer save(NhanVien nhanVien) {
        String sql = "INSERT INTO nhanvien"
                + " (MaNV, MaTK, Ho, Ten, NgaySinh, SDT, Email, GioiTinh, Luong)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, nhanVien.getMaNV(), nhanVien.getMaTK(), nhanVien.getHo(), nhanVien.getTen(), nhanVien.getNgaySinh(),
                nhanVien.getSdt(), nhanVien.getEmail(), nhanVien.getGioiTinh(), nhanVien.getLuong());
    }

    @Override
    public boolean update(NhanVien nhanVien) {
        String sql = "UPDATE nhanvien"
                + " SET MaTK = ?, Ho = ?, Ten = ?, NgaySinh = ?, SDT = ?, Email = ?, GioiTinh = ?, Luong = ?, TinhTrang = ?"
                + " WHERE MaNV = ?";
        return update(sql, nhanVien.getMaTK(), nhanVien.getHo(), nhanVien.getTen(), nhanVien.getNgaySinh(),
                nhanVien.getSdt(), nhanVien.getEmail(), nhanVien.getGioiTinh(), nhanVien.getLuong(), nhanVien.getTinhTrang(), nhanVien.getMaNV());
    }

    @Override
    public boolean delete(int MaNV) {
        String sql = "DELETE FROM nhanvien"
                + " WHERE MaNV = ?";
        return update(sql, MaNV);
    }
}
