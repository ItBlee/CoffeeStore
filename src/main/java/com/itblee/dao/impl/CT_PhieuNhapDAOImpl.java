package com.itblee.dao.impl;

import com.google.auto.service.AutoService;
import com.itblee.dao.CT_PhieuNhapDAO;
import com.itblee.dto.CT_PhieuNhap;
import com.itblee.mapper.CT_PhieuNhapMapper;
import com.itblee.Provider;

import java.util.List;

@AutoService(CT_PhieuNhapDAO.class)
public class CT_PhieuNhapDAOImpl extends AbstractDAO<CT_PhieuNhap> implements CT_PhieuNhapDAO {

    private CT_PhieuNhapMapper mapper = Provider.get(CT_PhieuNhapMapper.class);

    @Override
    public List<CT_PhieuNhap> findAll() {
        String sql = "SELECT * FROM ct_phieunhap";
        return query(sql, mapper);
    }

    @Override
    public CT_PhieuNhap findByID(int MaCTPN) {
        String sql = "SELECT * FROM ct_phieunhap WHERE MaCTPN = ?";
        List<CT_PhieuNhap> result = query(sql, mapper, MaCTPN);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(CT_PhieuNhap taiKhoan) {
        String sql = "INSERT INTO ct_phieunhap"
                + " (MaCTPN, MaPN, MaSP, SoLuong, DonGia, ThanhTien)"
                + " VALUES(?, ?, ?, ?, ?, ?)";
        return insert(sql, taiKhoan.getMaCTPN(), taiKhoan.getMaPN(), taiKhoan.getMaSP(),
                taiKhoan.getSoLuong(), taiKhoan.getDonGia(), taiKhoan.getThanhTien());
    }

    @Override
    public boolean update(CT_PhieuNhap taiKhoan) {
        String sql = "UPDATE ct_phieunhap"
                + " SET MaPN = ?, MaSP = ?, SoLuong = ?, DonGia = ?, ThanhTien = ?"
                + " WHERE MaCTPN = ?";
        return update(sql, taiKhoan.getMaPN(), taiKhoan.getMaSP(), taiKhoan.getSoLuong(),
                taiKhoan.getDonGia(), taiKhoan.getThanhTien(), taiKhoan.getMaCTPN());
    }

    @Override
    public boolean delete(int MaCTPN) {
        String sql = "DELETE FROM ct_phieunhap"
                + " WHERE MaCTPN = ?";
        return update(sql, MaCTPN);
    }
}
