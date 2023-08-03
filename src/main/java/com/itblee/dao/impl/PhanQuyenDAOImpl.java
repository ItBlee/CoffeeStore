package com.itblee.dao.impl;

import com.google.auto.service.AutoService;
import com.itblee.dao.PhanQuyenDAO;
import com.itblee.dto.PhanQuyen;
import com.itblee.mapper.PhanQuyenMapper;
import com.itblee.Provider;

import java.util.List;

@AutoService(PhanQuyenDAO.class)
public class PhanQuyenDAOImpl extends AbstractDAO<PhanQuyen> implements PhanQuyenDAO {

    private final PhanQuyenMapper mapper = Provider.get(PhanQuyenMapper.class);

    @Override
    public List<PhanQuyen> findAll() {
        String sql = "SELECT * FROM phanquyen";
        return query(sql, mapper);
    }

    @Override
    public PhanQuyen findByID(int MaPQ) {
        String sql = "SELECT * FROM phanquyen WHERE MaPQ = ?";
        List<PhanQuyen> result = query(sql, mapper, MaPQ);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Integer save(PhanQuyen dto) {
        String sql = "INSERT INTO phanquyen"
                + " (MaPQ, TenPQ, QuyenHD, QuyenSP, QuyenPN, QuyenNCC, QuyenKH, QuyenKM, QuyenTK, QuyenExcel, QuyenNV)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, dto.getMaPQ(), dto.getTenPQ(), dto.getQuyenHD(), dto.getQuyenSP(),
                dto.getQuyenPN(), dto.getQuyenNCC(), dto.getQuyenKH(), dto.getQuyenKM(),
                dto.getQuyenTK(), dto.getQuyenExcel(), dto.getQuyenNV());
    }

    @Override
    public boolean update(PhanQuyen dto) {
        String sql = "UPDATE phanquyen"
                + " SET TenPQ = ?, QuyenHD = ?, QuyenSP = ?,"
                + " QuyenPN = ?, QuyenNCC = ?, QuyenKH = ?, QuyenKM = ?, QuyenTK = ?, QuyenExcel = ?, QuyenNV = ?"
                + " WHERE MaPQ = ?";
        return update(sql, dto.getTenPQ(), dto.getQuyenHD(), dto.getQuyenSP(),
                dto.getQuyenPN(), dto.getQuyenNCC(), dto.getQuyenKH(), dto.getQuyenKM(),
                dto.getQuyenTK(), dto.getQuyenExcel(), dto.getQuyenNV(), dto.getMaPQ());
    }

    @Override
    public boolean delete(int MaPQ) {
        String sql = "DELETE FROM phanquyen"
                + " WHERE MaPQ = ?";
        return update(sql, MaPQ);
    }
}
