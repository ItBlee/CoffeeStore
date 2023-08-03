package com.itblee.dao.impl;

import com.google.auto.service.AutoService;
import com.itblee.dao.KhachHangDAO;
import com.itblee.dto.KhachHang;
import com.itblee.mapper.KhachHangMapper;
import com.itblee.Provider;

import java.util.List;

@AutoService(KhachHangDAO.class)
public class KhachHangDAOImpl extends AbstractDAO<KhachHang> implements KhachHangDAO {

    private final KhachHangMapper mapper = Provider.get(KhachHangMapper.class);

    @Override
    public List<KhachHang> findAll() {
        String sql = "SELECT * FROM khachhang";
        return query(sql, mapper);
    }

    @Override
    public KhachHang findByID(int MaKH) {
        String sql = "SELECT * FROM khachhang WHERE MaKH = ?";
        List<KhachHang> result = query(sql, mapper, MaKH);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Integer save(KhachHang khachHang) {
        String sql = "INSERT INTO khachhang"
                + " (MaKH, Ho, Ten, SDT, DiaChi, Email, TinhTrang)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, khachHang.getMaKH(), khachHang.getHo(), khachHang.getTen(), khachHang.getSdt(),
                khachHang.getDiaChi(), khachHang.getEmail() , khachHang.getTinhTrang());
    }

    @Override
    public boolean update(KhachHang khachHang) {
        String sql = "UPDATE khachhang"
                + " SET Ho = ?, Ten = ?, SDT = ?, DiaChi = ?, Email = ?, TinhTrang = ?"
                + " WHERE MaKH = ?";
        return update(sql, khachHang.getHo(), khachHang.getTen(), khachHang.getSdt(), khachHang.getDiaChi(),
                khachHang.getEmail() , khachHang.getTinhTrang(), khachHang.getMaKH());
    }

    @Override
    public boolean delete(int MaKH) {
        String sql = "DELETE FROM khachhang"
                + " WHERE MaKH = ?";
        return update(sql, MaKH);
    }
}
