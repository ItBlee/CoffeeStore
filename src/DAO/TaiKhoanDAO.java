package DAO;

import DAO.Abstracts.AbstractDAO;
import DAO.Interfaces.IDAO;
import DAO.Interfaces.ITaiKhoanDAO;
import DTO.TaiKhoanDTO;
import Mapper.TaiKhoanMapper;

import java.util.ArrayList;

public class TaiKhoanDAO extends AbstractDAO<TaiKhoanDTO> implements IDAO<TaiKhoanDTO>, ITaiKhoanDAO {
    @Override
    public ArrayList<TaiKhoanDTO> findAll() {
        String sql = "SELECT * FROM taikhoan";
        return query(sql, new TaiKhoanMapper());
    }

    @Override
    public TaiKhoanDTO findByID(int id) {
        String sql = "SELECT * FROM taikhoan WHERE MaTK = ?";
        ArrayList<TaiKhoanDTO> result = query(sql, new TaiKhoanMapper(), id);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Integer save(TaiKhoanDTO taiKhoan) {
        String sql = "INSERT INTO taikhoan"
                + "(TenDangNhap, MatKhau, NguoiTao, ChucVu, TinhTrang)"
                + "VALUES(?, ?, ?, ?, ?)";
        return insert(sql, taiKhoan.getTenDangNhap(), taiKhoan.getMatKhau(),
                    taiKhoan.getNguoiTao(), taiKhoan.getChucVu(), taiKhoan.getTinhTrang());
    }

    @Override
    public boolean update(TaiKhoanDTO taiKhoan) {
        String sql = "UPDATE taikhoan"
                + " SET TenDangNhap = ?, MatKhau = ?, NgayTao = ?, NguoiTao = ?, ChucVu = ?, TinhTrang = ?"
                + " WHERE MaTK = ?";
        return update(sql, taiKhoan.getTenDangNhap(), taiKhoan.getMatKhau(), taiKhoan.getNgayTao(),
                taiKhoan.getNguoiTao(), taiKhoan.getChucVu(), taiKhoan.getTinhTrang(), taiKhoan.getMaTK());
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM taikhoan"
                + " WHERE MaTK = ?";
        return update(sql, id);
    }
}
