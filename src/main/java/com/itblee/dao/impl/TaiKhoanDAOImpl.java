package com.itblee.dao.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dao.TaiKhoanDAO;
import com.itblee.dto.TaiKhoan;
import com.itblee.mapper.TaiKhoanMapper;

import java.util.ArrayList;

@AutoService(TaiKhoanDAO.class)
public class TaiKhoanDAOImpl extends AbstractDAO<TaiKhoan> implements TaiKhoanDAO {

    private final TaiKhoanMapper mapper = Provider.get(TaiKhoanMapper.class);

    @Override
    public ArrayList<TaiKhoan> findAll() {
        String sql = "SELECT * FROM taikhoan";
        return query(sql, mapper);
    }

    @Override
    public TaiKhoan findByID(int MaTK) {
        String sql = "SELECT * FROM taikhoan WHERE MaTK = ?";
        ArrayList<TaiKhoan> result = query(sql, mapper, MaTK);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(TaiKhoan taiKhoan) {
        String sql = "INSERT INTO taikhoan"
                + " (MaTK, TenDangNhap, MatKhauHash, MatKhauSalt, NgayTao, NguoiTao, MaPQ, TinhTrang)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, taiKhoan.getMaTK(), taiKhoan.getTenDangNhap(), taiKhoan.getMatKhauHash(), taiKhoan.getMatKhauSalt(),
                taiKhoan.getNgayTao(), taiKhoan.getNguoiTao(), taiKhoan.getMaPQ(), taiKhoan.getTinhTrang());
    }

    @Override
    public boolean update(TaiKhoan taiKhoan) {
        String sql = "UPDATE taikhoan"
                + " SET TenDangNhap = ?, MatKhauHash = ?, MatKhauSalt = ?, NgayTao = ?, NguoiTao = ?, MaPQ = ?, TinhTrang = ?"
                + " WHERE MaTK = ?";
        return update(sql, taiKhoan.getTenDangNhap(), taiKhoan.getMatKhauHash(), taiKhoan.getMatKhauSalt(), taiKhoan.getNgayTao(),
                taiKhoan.getNguoiTao(), taiKhoan.getMaPQ(), taiKhoan.getTinhTrang(), taiKhoan.getMaTK());
    }

    @Override
    public boolean delete(int MaTK) {
        String sql = "DELETE FROM taikhoan"
                + " WHERE MaTK = ?";
        return update(sql, MaTK);
    }
}
