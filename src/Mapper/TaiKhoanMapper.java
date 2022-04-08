package Mapper;

import DTO.TaiKhoanDTO;
import Mapper.Interfaces.IRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoanMapper implements IRowMapper<TaiKhoanDTO> {
    @Override
    public TaiKhoanDTO mapRow(ResultSet resultSet) {
        try {
            TaiKhoanDTO taiKhoan = new TaiKhoanDTO();
            taiKhoan.setMaTK(resultSet.getInt("MaTK"));
            taiKhoan.setTenDangNhap(resultSet.getString("TenDangNhap"));
            taiKhoan.setMatKhau(resultSet.getString("MatKhau"));
            taiKhoan.setNgayTao(resultSet.getTimestamp("NgayTao"));
            taiKhoan.setNguoiTao(resultSet.getInt("NguoiTao"));
            taiKhoan.setChucVu(resultSet.getString("ChucVu"));
            taiKhoan.setTinhTrang(resultSet.getInt("TinhTrang"));
            return taiKhoan;
        } catch (SQLException e) {
            return null;
        }
    }
}
