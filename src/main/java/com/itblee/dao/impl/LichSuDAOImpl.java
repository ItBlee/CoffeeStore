package com.itblee.dao.impl;

import com.google.auto.service.AutoService;
import com.itblee.dao.LichSuDAO;
import com.itblee.dto.LichSu;
import com.itblee.mapper.LichSuMapper;
import com.itblee.Provider;

import java.util.List;

@AutoService(LichSuDAO.class)
public class LichSuDAOImpl extends AbstractDAO<LichSu> implements LichSuDAO {

    private final LichSuMapper mapper = Provider.get(LichSuMapper.class);

    @Override
    public List<LichSu> findAll() {
        String sql = "SELECT * FROM lichsu";
        return query(sql, mapper);
    }

    @Override
    public LichSu findByID(int MaLS) {
        String sql = "SELECT * FROM lichsu WHERE MaLS = ?";
        List<LichSu> result = query(sql, mapper, MaLS);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Integer save(LichSu lichSu) {
        String sql = "INSERT INTO lichsu"
                + " (MaLS, TenDoiTuong, MaDoiTuong, NguoiThucHien, ThaoTac)"
                + " VALUES(?, ?, ?, ?, ?)";
        return insert(sql, lichSu.getMaLS(), lichSu.getTenDoiTuong(), lichSu.getMaDoiTuong(), lichSu.getNguoiThucHien(), lichSu.getThaoTac());
    }

    @Override
    public boolean update(LichSu lichSu) {
        String sql = "UPDATE lichsu"
                + " SET TenDoiTuong = ?, MaDoiTuong = ?, NguoiThucHien = ?, ThaoTac = ?"
                + " WHERE MaLS = ?";
        return update(sql, lichSu.getTenDoiTuong(), lichSu.getMaDoiTuong(), lichSu.getNguoiThucHien(), lichSu.getThaoTac(), lichSu.getMaLS());
    }

    @Override
    public boolean delete(int MaLS) {
        String sql = "DELETE FROM lichsu"
                + " WHERE MaLS = ?";
        return update(sql, MaLS);
    }
}
