package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.ITaiKhoanDAO;
import DTO.TaiKhoanDTO;
import DAO.Mapper.TaiKhoanMapper;

import java.util.ArrayList;

public class TaiKhoanDAO extends AbstractDAO<TaiKhoanDTO> implements ITaiKhoanDAO {
    @Override
    public ArrayList<TaiKhoanDTO> findAll() {
        String sql = "SELECT * FROM taikhoan";
        return query(sql, new TaiKhoanMapper());
    }

    @Override
    public TaiKhoanDTO findByID(int MaTK) {
        String sql = "SELECT * FROM taikhoan WHERE MaTK = ?";
        ArrayList<TaiKhoanDTO> result = query(sql, new TaiKhoanMapper(), MaTK);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(TaiKhoanDTO taiKhoan) {
        String sql = "INSERT INTO taikhoan"
                + " (MaTK, TenDangNhap, MatKhauHash, MatKhauSalt, NgayTao, NguoiTao, ChucVu, TinhTrang)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, taiKhoan.getMaTK(), taiKhoan.getTenDangNhap(), taiKhoan.getMatKhauHash(), taiKhoan.getMatKhauSalt(),
                taiKhoan.getNgayTao(), taiKhoan.getNguoiTao(), taiKhoan.getMaPQ(), taiKhoan.getTinhTrang());
    }

    @Override
    public boolean update(TaiKhoanDTO taiKhoan) {
        String sql = "UPDATE taikhoan"
                + " SET TenDangNhap = ?, MatKhauHash = ?, MatKhauSalt = ?, NgayTao = ?, NguoiTao = ?, ChucVu = ?, TinhTrang = ?"
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
