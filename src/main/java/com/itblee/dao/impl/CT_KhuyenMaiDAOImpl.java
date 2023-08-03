package com.itblee.dao.impl;

import com.google.auto.service.AutoService;
import com.itblee.dao.CT_KhuyenMaiDAO;
import com.itblee.dto.CT_KhuyenMai;
import com.itblee.mapper.CT_KhuyenMaiMapper;
import com.itblee.Provider;

import java.util.List;

@AutoService(CT_KhuyenMaiDAO.class)
public class CT_KhuyenMaiDAOImpl extends AbstractDAO<CT_KhuyenMai> implements CT_KhuyenMaiDAO {

    private final CT_KhuyenMaiMapper mapper = Provider.get(CT_KhuyenMaiMapper.class);

    @Override
    public List<CT_KhuyenMai> findAll() {
        String sql = "SELECT * FROM ct_khuyenmai";
        return query(sql, mapper);
    }

    @Override
    public CT_KhuyenMai findByID(int MaCTKM) {
        String sql = "SELECT * FROM ct_khuyenmai WHERE MaCTKM = ?";
        List<CT_KhuyenMai> result = query(sql, mapper, MaCTKM);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(CT_KhuyenMai ctKhuyenMai) {
        String sql = "INSERT INTO ct_khuyenmai"
                + " (MaCTKM, MaKM, MaSP, GiamGia)"
                + " VALUES(?, ?, ?, ?)";
        return insert(sql, ctKhuyenMai.getMaCTKM(), ctKhuyenMai.getMaKM(), ctKhuyenMai.getMaSP(), ctKhuyenMai.getGiamGia());
    }

    @Override
    public boolean update(CT_KhuyenMai ctKhuyenMai) {
        String sql = "UPDATE ct_khuyenmai"
                + " SET MaKM = ?, MaSP = ?, GiamGia = ?"
                + " WHERE MaCTKM = ?";
        return update(sql, ctKhuyenMai.getMaKM(), ctKhuyenMai.getMaSP(), ctKhuyenMai.getGiamGia(), ctKhuyenMai.getMaCTKM());
    }

    @Override
    public boolean delete(int MaCTKM) {
        String sql = "DELETE FROM ct_khuyenmai"
                + " WHERE MaCTKM = ?";
        return update(sql, MaCTKM);
    }
}
