package com.itblee.dao.impl;

import com.google.auto.service.AutoService;
import com.itblee.dao.LoaiSPDAO;
import com.itblee.dto.LoaiSP;
import com.itblee.mapper.LoaiSPMapper;
import com.itblee.Provider;

import java.util.List;

@AutoService(LoaiSPDAO.class)
public class LoaiSPDAOImpl extends AbstractDAO<LoaiSP> implements LoaiSPDAO {

    private final LoaiSPMapper mapper = Provider.get(LoaiSPMapper.class);

    @Override
    public List<LoaiSP> findAll() {
        String sql = "SELECT * FROM loaisp";
        return query(sql, mapper);
    }

    @Override
    public LoaiSP findByID(int MaLoai) {
        String sql = "SELECT * FROM loaisp WHERE MaLoai = ?";
        List<LoaiSP> result = query(sql, mapper, MaLoai);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(LoaiSP loaiSP) {
        String sql = "INSERT INTO loaisp"
                + " (MaLoai, TenLoai, MoTa)"
                + " VALUES(?, ?, ?)";
        return insert(sql, loaiSP.getMaLoai(), loaiSP.getTenLoai(), loaiSP.getMoTa());
    }

    @Override
    public boolean update(LoaiSP loaiSP) {
        String sql = "UPDATE loaisp"
                + " SET TenLoai = ?, MoTa = ?"
                + " WHERE MaLoai = ?";
        return update(sql, loaiSP.getTenLoai(), loaiSP.getMoTa(), loaiSP.getMaLoai());
    }

    @Override
    public boolean delete(int MaLoai) {
        String sql = "DELETE FROM loaisp"
                + " WHERE MaLoai = ?";
        return update(sql, MaLoai);
    }
}
