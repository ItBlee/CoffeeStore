package com.itblee.dao.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dao.PhieuNhapDAO;
import com.itblee.dto.PhieuNhap;
import com.itblee.mapper.PhieuNhapMapper;

import java.util.ArrayList;

@AutoService(PhieuNhapDAO.class)
public class PhieuNhapDAOImpl extends AbstractDAO<PhieuNhap> implements PhieuNhapDAO {

    private PhieuNhapMapper mapper = Provider.get(PhieuNhapMapper.class);

    @Override
    public ArrayList<PhieuNhap> findAll() {
        String sql = "SELECT * FROM phieunhap";
        return query(sql, mapper);
    }

    @Override
    public PhieuNhap findByID(int MaPN) {
        String sql = "SELECT * FROM phieunhap WHERE MaPN = ?";
        ArrayList<PhieuNhap> result = query(sql, mapper, MaPN);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(PhieuNhap phieuNhap) {
        String sql = "INSERT INTO phieunhap"
                + " (MaPN, MaNCC, MaNV, NgayTao, TongTien, TinhTrang)"
                + " VALUES(?, ?, ?, ?, ?, ?)";
        return insert(sql, phieuNhap.getMaPN(), phieuNhap.getMaNCC(), phieuNhap.getMaNV(),
                phieuNhap.getNgayTao(), phieuNhap.getTongTien(), phieuNhap.getTinhTrang());
    }

    @Override
    public boolean update(PhieuNhap phieuNhap) {
        String sql = "UPDATE phieunhap"
                + " SET MaNCC = ?, MaNV = ?, NgayTao = ?, TongTien = ?, TinhTrang = ?"
                + " WHERE MaPN = ?";
        return update(sql, phieuNhap.getMaNCC(), phieuNhap.getMaNV(), phieuNhap.getNgayTao(),
                phieuNhap.getTongTien(), phieuNhap.getTinhTrang(), phieuNhap.getMaPN());
    }

    @Override
    public boolean delete(int MaPN) {
        String sql = "DELETE FROM phieunhap"
                + " WHERE MaPN = ?";
        return update(sql, MaPN);
    }
}
