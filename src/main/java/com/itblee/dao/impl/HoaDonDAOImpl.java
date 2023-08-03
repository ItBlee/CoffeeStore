package com.itblee.dao.impl;

import com.google.auto.service.AutoService;
import com.itblee.dao.HoaDonDAO;
import com.itblee.dto.HoaDon;
import com.itblee.mapper.HoaDonMapper;
import com.itblee.Provider;

import java.util.List;

@AutoService(HoaDonDAO.class)
public class HoaDonDAOImpl extends AbstractDAO<HoaDon> implements HoaDonDAO {

    private final HoaDonMapper mapper = Provider.get(HoaDonMapper.class);

    @Override
    public List<HoaDon> findAll() {
        String sql = "SELECT * FROM hoadon";
        return query(sql, mapper);
    }

    @Override
    public HoaDon findByID(int MaHD) {
        String sql = "SELECT * FROM hoadon WHERE MaHD = ?";
        List<HoaDon> result = query(sql, mapper, MaHD);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(HoaDon hoaDon) {
        String sql = "INSERT INTO hoadon"
                + " (MaHD, MaKH, MaNV, NgayLap, TongTien, TienKhuyenMai, TienThanhToan, TinhTrang)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, hoaDon.getMaHD(), hoaDon.getMaKH(), hoaDon.getMaNV(), hoaDon.getNgayLap(),
                hoaDon.getTongTien(), hoaDon.getTienKhuyenMai(), hoaDon.getTienThanhToan(), hoaDon.getTinhTrang());
    }

    @Override
    public boolean update(HoaDon hoaDon) {
        String sql = "UPDATE hoadon"
                + " SET MaKH = ?, MaNV = ?, NgayLap = ?, TongTien = ?, TienKhuyenMai = ?, TienThanhToan = ?, TinhTrang = ?"
                + " WHERE MaHD = ?";
        return update(sql, hoaDon.getMaKH(), hoaDon.getMaNV(), hoaDon.getNgayLap(), hoaDon.getTongTien(),
                hoaDon.getTienKhuyenMai(), hoaDon.getTienThanhToan(), hoaDon.getTinhTrang(), hoaDon.getMaHD());
    }

    @Override
    public boolean delete(int MaHD) {
        String sql = "DELETE FROM hoadon"
                + " WHERE MaHD = ?";
        return update(sql, MaHD);
    }
}
