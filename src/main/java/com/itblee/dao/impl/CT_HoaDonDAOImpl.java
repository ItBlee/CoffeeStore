package com.itblee.dao.impl;

import com.google.auto.service.AutoService;
import com.itblee.dao.CT_HoaDonDAO;
import com.itblee.dto.CT_HoaDon;
import com.itblee.mapper.CT_HoaDonMapper;
import com.itblee.Provider;

import java.util.List;

@AutoService(CT_HoaDonDAO.class)
public class CT_HoaDonDAOImpl extends AbstractDAO<CT_HoaDon> implements CT_HoaDonDAO {

    private final CT_HoaDonMapper mapper = Provider.get(CT_HoaDonMapper.class);

    @Override
    public List<CT_HoaDon> findAll() {
        String sql = "SELECT * FROM ct_hoadon";
        return query(sql, mapper);
    }

    @Override
    public CT_HoaDon findByID(int MaCTHD) {
        String sql = "SELECT * FROM ct_hoadon WHERE MaCTHD = ?";
        List<CT_HoaDon> result = query(sql, mapper, MaCTHD);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Integer save(CT_HoaDon ctHoaDon) {
        String sql = "INSERT INTO ct_hoadon"
                + " (MaCTHD, MaHD, MaSP, SoLuong, DonGia, TienKhuyenMai, ThanhTien)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, ctHoaDon.getMaCTHD(), ctHoaDon.getMaHD(), ctHoaDon.getMaSP(), ctHoaDon.getSoLuong(),
                ctHoaDon.getDonGia(), ctHoaDon.getTienKhuyenMai(), ctHoaDon.getThanhTien());
    }

    @Override
    public boolean update(CT_HoaDon ctHoaDon) {
        String sql = "UPDATE ct_hoadon"
                + " SET MaHD = ?, MaSP = ?, SoLuong = ?, DonGia = ?, TienKhuyenMai = ?, ThanhTien = ?"
                + " WHERE MaCTHD = ?";
        return update(sql, ctHoaDon.getMaHD(), ctHoaDon.getMaSP(), ctHoaDon.getSoLuong(),
                ctHoaDon.getDonGia(), ctHoaDon.getTienKhuyenMai(), ctHoaDon.getThanhTien(), ctHoaDon.getMaCTHD());
    }

    @Override
    public boolean delete(int MaCTHD) {
        String sql = "DELETE FROM ct_hoadon"
                + " WHERE MaCTHD = ?";
        return update(sql, MaCTHD);
    }

}
