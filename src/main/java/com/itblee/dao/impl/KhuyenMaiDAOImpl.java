package com.itblee.dao.impl;

import com.google.auto.service.AutoService;
import com.itblee.dao.KhuyenMaiDAO;
import com.itblee.dto.KhuyenMai;
import com.itblee.mapper.KhuyenMaiMapper;
import com.itblee.Provider;

import java.util.List;

@AutoService(KhuyenMaiDAO.class)
public class KhuyenMaiDAOImpl extends AbstractDAO<KhuyenMai> implements KhuyenMaiDAO {

    private final KhuyenMaiMapper mapper = Provider.get(KhuyenMaiMapper.class);

    @Override
    public List<KhuyenMai> findAll() {
        String sql = "SELECT * FROM khuyenmai";
        return query(sql, mapper);
    }

    @Override
    public KhuyenMai findByID(int MaKM) {
        String sql = "SELECT * FROM khuyenmai WHERE MaKM = ?";
        List<KhuyenMai> result = query(sql, mapper, MaKM);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(KhuyenMai khuyenMai) {
        String sql = "INSERT INTO khuyenmai"
                + " (MaKM, TieuDe, NoiDung, NgayBD, NgayKT, TinhTrang)"
                + " VALUES(?, ?, ?, ?, ?, ?)";
        return insert(sql, khuyenMai.getMaKM(), khuyenMai.getTieuDe(), khuyenMai.getNoiDung(),
                khuyenMai.getNgayBD(), khuyenMai.getNgayKT(), khuyenMai.getTinhTrang());
    }

    @Override
    public boolean update(KhuyenMai khuyenMai) {
        String sql = "UPDATE khuyenmai"
                + " SET TieuDe = ?, NoiDung = ?, NgayBD = ?, NgayKT = ?, TinhTrang = ?"
                + " WHERE MaKM = ?";
        return update(sql, khuyenMai.getTieuDe(), khuyenMai.getNoiDung(),
                khuyenMai.getNgayBD(), khuyenMai.getNgayKT(), khuyenMai.getTinhTrang(), khuyenMai.getMaKM());
    }

    @Override
    public boolean delete(int MaKM) {
        String sql = "DELETE FROM khuyenmai"
                + " WHERE MaKM = ?";
        return update(sql, MaKM);
    }
}
