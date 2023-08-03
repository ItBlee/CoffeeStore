package com.itblee.dao.impl;

import com.google.auto.service.AutoService;
import com.itblee.dao.CT_PhanQuyenDAO;
import com.itblee.dto.CT_PhanQuyen;
import com.itblee.mapper.CT_PhanQuyenMapper;
import com.itblee.Provider;

import java.util.List;

@AutoService(CT_PhanQuyenDAO.class)
public class CT_PhanQuyenDAOImpl extends AbstractDAO<CT_PhanQuyen> implements CT_PhanQuyenDAO {

    private final CT_PhanQuyenMapper mapper = Provider.get(CT_PhanQuyenMapper.class);

    @Override
    public List<CT_PhanQuyen> findAll() {
        String sql = "SELECT * FROM ct_phanquyen";
        return query(sql, mapper);
    }

    @Override
    public CT_PhanQuyen findByID(int MaCTPQ) {
        String sql = "SELECT * FROM ct_phanquyen WHERE MaCTPQ = ?";
        List<CT_PhanQuyen> result = query(sql, mapper, MaCTPQ);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Integer save(CT_PhanQuyen dto) {
        String sql = "INSERT INTO ct_phanquyen"
                + " (MaCTPQ, QuyenDoc, QuyenTao, QuyenSua, QuyenXoa)"
                + " VALUES(?, ?, ?, ?, ?)";
        return insert(sql, dto.getMaCTPQ(), dto.getQuyenDoc(), dto.getQuyenTao(), dto.getQuyenSua(), dto.getQuyenXoa());
    }

    @Override
    public boolean update(CT_PhanQuyen dto) {
        String sql = "UPDATE ct_phanquyen"
                + " SET QuyenDoc = ?, QuyenTao = ?, QuyenSua = ?, QuyenXoa = ?"
                + " WHERE MaCTPQ = ?";
        return update(sql, dto.getQuyenDoc(), dto.getQuyenTao(), dto.getQuyenSua(), dto.getQuyenXoa(), dto.getMaCTPQ());
    }

    @Override
    public boolean delete(int MaCTPQ) {
        String sql = "DELETE FROM ct_phanquyen"
                + " WHERE MaCTPQ = ?";
        return update(sql, MaCTPQ);
    }
}
